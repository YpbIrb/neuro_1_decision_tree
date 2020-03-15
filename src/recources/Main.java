package recources;

import MyCSVParser.CSVParser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            File fin = new File("train_entities.csv");
            List<DataEntity> train_entities;
            train_entities = CSVParser.parseCSV(fin);

            DataEntity attrs = train_entities.remove(0);
            DataSet data = new DataSet(train_entities, attrs.getAttr_vals());
            System.out.println(data);
            TreeNode tree_root = new TreeNode();
            DecisionTree tree = new DecisionTree(tree_root);
            tree.TrainTree(tree.getRoot(), data);
            System.out.println(tree);

            System.out.println("!!!!CLASSIFIER!!!!");


            Classifier classifier = new Classifier(tree);
            File fclassify = new File("classify_entities.csv");
            List<DataEntity> to_classify_entities = CSVParser.parseCSV(fclassify);

            List<String> classify_res = classifier.Classify(to_classify_entities, data.getAttributes());
            System.out.println(classify_res);

        } catch (IOException e) {
            System.out.println("!!ERROR IN FILE READING!!");
            e.printStackTrace();
        }

    }






}
