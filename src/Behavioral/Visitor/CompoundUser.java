package Behavioral.Visitor;

import java.util.ArrayList;
import java.util.List;

public class CompoundUser implements Shape{
    public int id;
    public List<String> children = new ArrayList<>();

    public CompoundUser(int id){
        this.id = id;
    }
    public String accept(Visitor visitor){
        return  visitor.CompoundUser(this);
    }
    public void add(String shape){
        children.add(shape);
    }
}
