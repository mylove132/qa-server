package com.blingabc.auto.beans;

import java.util.List;

public class CatalogVO {
    private Integer id;

    private String text;

    private String icon;

    private Integer parentId;

    private String type;

    private List<CatalogVO> children;

    public List<CatalogVO> getChildren() {
        return children;
    }

    public void setChildren(List<CatalogVO> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}