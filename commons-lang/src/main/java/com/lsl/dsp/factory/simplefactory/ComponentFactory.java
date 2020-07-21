package com.lsl.dsp.factory.simplefactory;

public class ComponentFactory {
    public static IComponent createComponent(int type) {
        IComponent component = null;
        if (type == 1) {
            component = new ComponentAImpl();
        } else {
            component = new ComponentBImpl();
        }
        return component;
    }
}
