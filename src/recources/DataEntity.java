package recources;

import java.util.List;

public class DataEntity {
    private List<String> attr_vals;

    public DataEntity(List<String> attrVals) {
        this.attr_vals = attrVals;
    }

    public List<String> getAttr_vals() {
        return attr_vals;
    }

    @Override
    public String toString() {
        return "DataEntity{" +
                "attr_vals=" + attr_vals +
                '}';
    }
}
