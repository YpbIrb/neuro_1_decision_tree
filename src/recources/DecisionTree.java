package recources;

import java.util.Map;

public class DecisionTree {
    private TreeNode root;

    public DecisionTree() {
        root = new TreeNode();
    }

    public DecisionTree(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void MakeLeaf(TreeNode node, String attr_val, Map<String, Integer> freq){
        int max_freq = 0;
        String new_label;
        for (Map.Entry<String, Integer> entry: freq.entrySet()){
            if(max_freq < entry.getValue()){
                max_freq = entry.getValue();
                new_label = entry.getKey();
            }
        }

        node.AddChild(attr_val, new TreeNode(new_label));

    }

}
