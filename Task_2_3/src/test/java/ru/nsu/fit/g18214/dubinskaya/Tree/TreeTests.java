package ru.nsu.fit.g18214.dubinskaya.Tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class TreeTests {

  @Test
  public void exampleTest() {
    Tree<String> nsu = new Tree<>("НГУ");
    nsu.addChild("ММФ", "НГУ");
    nsu.addChild("ФФ", "НГУ");
    nsu.addChild("ФИТ", "НГУ");
    nsu.addChild("ГГФ", "НГУ");
    nsu.addChild("СИ", "НГУ", "ФИТ");
    nsu.addChild("КОИ", "НГУ", "ФИТ");
    nsu.setIterationType(Tree.BFS);
    Iterator<String> iter = nsu.iterator();
    Assert.assertEquals("НГУ", iter.next());
    Assert.assertEquals("ММФ", iter.next());
    Assert.assertEquals("ФФ", iter.next());
    Assert.assertEquals("ФИТ", iter.next());
    Assert.assertEquals("ГГФ", iter.next());
    Assert.assertEquals("СИ", iter.next());
    Assert.assertEquals("КОИ", iter.next());
    nsu.setIterationType(Tree.DFS);
    Assert.assertFalse(iter.hasNext());
  }

  @Test
  public void findingChild() {
    Tree<String> nsu = new Tree<>("НГУ");
    nsu.addChild("ММФ", "НГУ");
    nsu.addChild("ФФ", "НГУ");
    nsu.addChild("ФИТ", "НГУ");
    nsu.addChild("ГГФ", "НГУ");
    nsu.addChild("СИ", "НГУ", "ФИТ");
    nsu.addChild("КОИ", "НГУ", "ФИТ");
    Assert.assertTrue(nsu.deleteChild("ФИТ", "НГУ"));
    Assert.assertFalse(nsu.deleteChild("ФИТ", "НГУ"));
  }

  @Test
  public void addSubTree(){
    Tree<String> nsu = new Tree<>("НГУ");
    nsu.addChild("ММФ", "НГУ");
    nsu.addChild("ФФ", "НГУ");
    nsu.addChild("ГГФ", "НГУ");
    Tree<String> fit = new Tree<>("ФИТ");
    fit.addChild("СИ", "ФИТ");
    fit.addChild("КОИ", "ФИТ");
    nsu.addSubTree(fit);
    nsu.setIterationType(Tree.BFS);
    Iterator<String> iter = nsu.iterator();
    Assert.assertEquals("НГУ", iter.next());
    Assert.assertEquals("ММФ", iter.next());
    Assert.assertEquals("ФФ", iter.next());
    Assert.assertEquals("ГГФ", iter.next());
    fit.deleteChild("КОИ", "ФИТ");
    Assert.assertEquals("ФИТ", iter.next());
    Assert.assertEquals("СИ", iter.next());
    Assert.assertEquals("КОИ", iter.next());
    nsu.setIterationType(Tree.DFS);
    Assert.assertFalse(iter.hasNext());

  }
  @Test
  public void nullPointerException(){
    Tree<String> nsu = new Tree<>("НГУ");
    try{
      nsu.addChild("НГУ", (String) null);
      Assert.fail();
    } catch (NullPointerException ignored){}
    try{
      nsu.addChild(null, "122");
      Assert.fail();
    } catch (NullPointerException ignored){}
    try{
      nsu.deleteChild(null, "a");
      Assert.fail();
    } catch (NullPointerException ignored){}
  }
}
