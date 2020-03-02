package recources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSet {
    private List<String> attributes;
    private Map<Integer, List<String>>attr_vals;
    private List<DataEntity> entities;

    public DataSet(List<DataEntity> entities, List<String> attributes) {
        attr_vals = new HashMap<>();

        this.attributes = attributes;
        this.entities = entities;
        entities.forEach(entity->{
            for (int i = 0; i < entity.getAttr_vals().size(); i++){
                if (!attr_vals.containsKey(i))
                    attr_vals.put(i, new ArrayList<>());

                attr_vals.get(i).add(entity.getAttr_vals().get(i));
            }
        });

    }

    private float getInfo(){

    }

    private float GetSplitInfo(Map<String, List<DataEntity>> divided_groups){

    }

    private float getAttrInfo(int attr_id){

    }

    private Map<String, Integer> getAttrFreq(int attr_id){

    }

    private Map<String, List<DataEntity>> DivideByAttr(int attr_id){

    }



}
