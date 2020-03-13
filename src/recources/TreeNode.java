package recources;

import java.util.HashMap;
import java.util.Map;

public class TreeNode {
    private String attr_name;
    private String final_val;
    private Map<String, TreeNode> childrens;

    public TreeNode(String attr_name, String final_val) {
        this.attr_name = attr_name;
        this.final_val = final_val;
        childrens = new HashMap<>();
    }

    public TreeNode() {
        this.attr_name = null;
        final_val = null;
        childrens = new HashMap<>();
    }

    public TreeNode(String attr_name) {
        this.attr_name = attr_name;
        final_val = null;
        childrens = new HashMap<>();
    }

    public void AddChild(String attr_val, TreeNode node){
        childrens.put(attr_val, node);
    }

}
