package recources;

import java.util.*;


public class DataSet {
    private List<String> attributes;
    private Map<Integer, List<String>>attr_vals;
    private List<DataEntity> train_entities;

    public DataSet(List<DataEntity> train_entities, List<String> attributes) {
        attr_vals = new HashMap<>();

        this.attributes = attributes;
        this.train_entities = train_entities;
        train_entities.forEach(entity->{
            for (int i = 0; i < entity.getAttr_vals().size(); i++){
                if (!attr_vals.containsKey(i))
                    attr_vals.put(i, new ArrayList<>());

                attr_vals.get(i).add(entity.getAttr_vals().get(i));
            }
        });

    }

    public float getInfo(List <DataEntity> entities){
        float res = 0;
        Map<String, Integer> label_freq = getAttrFreq(attributes.size() - 1, entities);
        for(Map.Entry<String, Integer> entry: label_freq.entrySet()){
            float prob = (float)entry.getValue()/(float)entities.size();
            if (prob != 0)
                res += prob*CustomLog.Log(prob, 2);
        }

        return -res;
    }

    public float getSplitInfo(Map<String, List<DataEntity>> divided_groups){
        float res = 0;
        for (Map.Entry<String,List<DataEntity>> entry: divided_groups.entrySet()){
            float prob = (float)entry.getValue().size()/(float)train_entities.size();
            if (prob != 0)
                res += prob*CustomLog.Log(prob, 2);
        }
        return -res;
    }

    public float getAttrInfo(Map<String, List<DataEntity>> divided){
        int total_count = train_entities.size();
        float res = 0;
        for (Map.Entry<String, List<DataEntity>> entry: divided.entrySet()){
            res += ((float)entry.getValue().size() / (float)total_count) * getInfo(entry.getValue());
        }

        return res;
    }

    public float getAttrGainRatio(int attr_id){
        Map<String, List<DataEntity>> divided = DivideByAttr(attr_id);
        float attr_info = getAttrInfo(divided);
        float split_info = getSplitInfo(divided);
        float info = getInfo(train_entities);
        System.out.println("attr_info = " + attr_info + " split_info = " + split_info + " info = " + info);
        float res = (info - attr_info)/split_info;

        return res;
    }

    public Map<String, Integer> getAttrFreq(int attr_id, List <DataEntity> entities){
        Map<String, Integer> res = new HashMap<>();
        Map<Integer, List<String>> tmp_attr_vals = new HashMap<>();
        entities.forEach(entity->{
            for (int i = 0; i < entity.getAttr_vals().size(); i++){
                if (!tmp_attr_vals.containsKey(i))
                    tmp_attr_vals.put(i, new ArrayList<>());

                tmp_attr_vals.get(i).add(entity.getAttr_vals().get(i));
            }
        });

        tmp_attr_vals.get(attr_id).forEach(entity -> {
            if (res.containsKey(entity))
                res.put(entity, res.get(entity) + 1);

            else
                res.put(entity, 1);

        });

        return res;
    }

    public Map<String, List<DataEntity>> DivideByAttr(int attr_id){
        Map<String, List<DataEntity>> res = new HashMap<>();
        train_entities.forEach(data_ent ->{
            String new_val = data_ent.getAttr_vals().get(attr_id);
            if (res.containsKey(new_val)){
                res.get(new_val).add(data_ent);
            }
            else{
                res.put(new_val, new ArrayList<>());
                res.get(new_val).add(data_ent);
            }


        });
        return res;
    }


    public List<String> getAttributes() {
        return attributes;
    }

    public Map<Integer, List<String>> getAttr_vals() {
        return attr_vals;
    }

    public List<DataEntity> getTrain_entities() {
        return train_entities;
    }

    @Override
    public String toString() {
        return "DataSet{" +
                "attributes=" + attributes +
                ", attr_vals=" + attr_vals +
                ", train_entities=" + train_entities +
                '}';
    }
}
