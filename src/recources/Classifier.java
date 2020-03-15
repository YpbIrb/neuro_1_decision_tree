package recources;

import java.util.ArrayList;
import java.util.List;

public class Classifier {
    DecisionTree tree;

    public List<String> Classify(List<DataEntity> entities, List<String> attrs){
        TreeNode node = tree.getRoot();
        List<String> res = new ArrayList<>();

        if(node == null){
            res.add("NO TREE");
            return res;
        }


        for (int i = 0; i < entities.size(); i++){
            while (node.getFinal_val() == null){
                String curr_attr = node.getAttr_name();
                int attr_id = attrs.indexOf(curr_attr);
                String entity_attr_val = entities.get(i).getAttr_vals().get(attr_id);
                node = node.getChildrens().get(entity_attr_val);
            }

            res.add(node.getFinal_val());

            node = tree.getRoot();

        }


        return res;
    }


    public Classifier(DecisionTree tree) {
        this.tree = tree;
    }

    public DecisionTree getTree() {
        return tree;
    }

    public void setTree(DecisionTree tree) {
        this.tree = tree;
    }
}
