package recources;

import java.util.List;
import java.util.Map;

public class DecisionTree {
    private TreeNode root;

    public DecisionTree(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void MakeLeaf(TreeNode node, String attr_val, Map<String, Integer> freq){
        int max_freq = 0;
        String new_label = new String();
        for (Map.Entry<String, Integer> entry: freq.entrySet()){
            if(max_freq < entry.getValue()){
                max_freq = entry.getValue();
                new_label = entry.getKey();
            }
        }

        node.AddChild(attr_val, new TreeNode(null,new_label));
    }

    boolean TrainTree(TreeNode node, DataSet data){
        if  (node == null | data.getTrain_entities().isEmpty())
            return false;

        List<String> attrs = data.getAttributes();
        List<DataEntity> entities = data.getTrain_entities();
        Map<String, Integer> classFreq = data.getAttrFreq(attrs.size() - 1, entities);

        if (classFreq.size() == 1){
            node.setFinal_val(entities.get(0).getAttr_vals().get(attrs.size() - 1));
            return true;
        }

        float info = data.getInfo(entities);
        float max_gain = -Float.MAX_VALUE;
        int curr_attr = 0;

        for (int i = 0; i < attrs.size() - 1; i++){
            float attr_gain = data.getAttrGainRatio(i);
            System.out.println("i =" + i + " attr_gain = " + attr_gain );
            if (attr_gain > max_gain){
                max_gain = attr_gain;
                curr_attr = i;
            }
        }

        System.out.println("!!!!!!!! curr_attr = " + curr_attr + " max_gain = " + max_gain);
        node.setAttr_name(attrs.get(curr_attr));
        Map<String, List<DataEntity>> divided = data.DivideByAttr(curr_attr);
        divided.forEach((s, dataEntities) -> {
            TreeNode child = new TreeNode();
            node.AddChild(s, child);
            if(!TrainTree(child, new DataSet(dataEntities, attrs))){
                MakeLeaf(node, s, classFreq);
            }


        });

        return true;
    }

    @Override
    public String toString() {
        return "DecisionTree{" +
                "root=" + root +
                '}';
    }
}
