package recources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



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



    private float getInfo(){
        float res = 0;
        Map<String, Integer> label_freq = getAttrFreq(attributes.size() - 1);
        for(Map.Entry<String, Integer> entry: label_freq.entrySet()){
            float prob = entry.getValue()/train_entities.size();
            res += prob*CustomLog.Log(prob, 2);
        }

        return res;
    }

    private float GetSplitInfo(Map<String, List<DataEntity>> divided_groups){
        float res = 0;
        for (Map.Entry<String,List<DataEntity>> entry: divided_groups.entrySet()){
            float prob = entry.getValue().size()/train_entities.size();
            res += prob*CustomLog.Log(prob, 2);
        }
        return res;
    }

    private float getAttrInfo(int attr_id){
        Map<String, List<DataEntity>> divided = DivideByAttr(attr_id);
        int total_count = train_entities.size();
        float res = 0;
        for (Map.Entry<String, List<DataEntity>> entry: divided.entrySet()){
            res += entry.getValue().size() / total_count * getInfo();
        }

        return res;
    }

    private Map<String, Integer> getAttrFreq(int attr_id){
        Map<String, Integer> res = new HashMap<>();
        attr_vals.get(attr_id).forEach(entity -> {
            if (res.containsKey(entity))
                res.put(entity, res.get(entity) + 1);

            else
                res.put(entity, 1);

        });

        return res;
    }

    private Map<String, List<DataEntity>> DivideByAttr(int attr_id){
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

    private void MakeLeaf(){

    }


}
