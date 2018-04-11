package demo.com.xiongyantest01.bean;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author by xiongyan on 2018/4/9.
 */
public class TabActivityItemBean {
    private int itemType;
    private String itemValue;
    private ArrayList<String> tabList;
    private ArrayList<String> itemValueList;
    private Map<Integer, ArrayList<String>> valueMap;

    public Map<Integer, ArrayList<String>> getValueMap() {
        return valueMap;
    }

    public void setValueMap(Map<Integer, ArrayList<String>> valueMap) {
        this.valueMap = valueMap;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getItemValue() {
        return itemValue == null ? "" : itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public ArrayList<String> getTabList() {
        if (tabList == null) {
            return new ArrayList<>();
        }
        return tabList;
    }

    public void setTabList(ArrayList<String> tabList) {
        this.tabList = tabList;
    }

    public ArrayList<String> getItemValueList() {
        if (itemValueList == null) {
            return new ArrayList<>();
        }
        return itemValueList;
    }

    public void setItemValueList(ArrayList<String> itemValueList) {
        this.itemValueList = itemValueList;
    }
}
