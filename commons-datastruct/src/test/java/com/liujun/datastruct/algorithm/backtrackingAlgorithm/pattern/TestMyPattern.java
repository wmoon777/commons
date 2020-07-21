package com.liujun.datastruct.algorithm.backtrackingAlgorithm.pattern;

import org.junit.Test;

/**
 * 测试正则表达式
 *
 * @author liujun
 * @version 0.0.1
 * @date 2018/12/25
 */
public class TestMyPattern {

    @Test
    public void pattern1() {
        String patStr = "abc*";
        MyPattern instance = new MyPattern(patStr);
        boolean matRsp = instance.match("abc123abcde");
        System.out.println("match rsp :" + matRsp);
    }

    @Test
    public void pattern2() {
        String patStr = "abc*";
        MyPattern instance = new MyPattern(patStr);
        boolean matRsp = instance.match("ab123abcde");
        System.out.println("match rsp :" + matRsp);
    }

    @Test
    public void pattern3() {
        String patStr = "abc*";
        MyPattern instance = new MyPattern(patStr);
        boolean matRsp = instance.match("abc");
        System.out.println("match rsp :" + matRsp);
    }

    @Test
    public void pattern4() {
        String patStr = "abc?de";
        MyPattern instance = new MyPattern(patStr);
        boolean matRsp = instance.match("abcfde");
        System.out.println("match rsp :" + matRsp);
    }

    @Test
    public void pattern5() {
        String patStr = "*";
        MyPattern instance = new MyPattern(patStr);
        boolean matRsp = instance.match("abcfde");
        System.out.println("match rsp :" + matRsp);
    }
}
