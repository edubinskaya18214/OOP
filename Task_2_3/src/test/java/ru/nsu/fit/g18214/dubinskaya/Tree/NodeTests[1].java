package ru.nsu.fit.g18214.dubinskaya.Tree;

import org.junit.Assert;
import org.junit.Test;

public class NodeTests {
  @Test
  public void findingChild(){
    Node<String> nsu = new Node<>("НГУ");
    Node<String> fit = new Node<>("ФИТ");
    fit.addChild("СИ");
    fit.addChild("КОИ");
    nsu.addChild("ММФ");
    nsu.addChild("ФФ");
    nsu.addChild(fit);
    nsu.addChild("ГГФ");
    Node<String> fit2 = nsu.getChild("ФИТ");
    Assert.assertNotNull(nsu.getChild("ФИТ"));
    nsu.deleteChild("ФИТ");
    Assert.assertNull(nsu.getChild("ФИТ"));
  }

  @Test
  public void addChild(){
    Node<String> nsu = new Node<>("НГУ");
    Node<String> fit = new Node<>("ФИТ");
    fit.addChild("СИ");
    fit.addChild("КОИ");
    nsu.addChild("ММФ");
    nsu.addChild("ФФ");
    nsu.addChild(fit);
    nsu.addChild("ГГФ");
    Assert.assertNull(nsu.getChild("Абра-кадабра"));
    nsu.addChild("Абра-кадабра");
    Assert.assertNotNull(nsu.getChild("Абра-кадабра"));
  }

  @Test
  public void nullPointerException(){
    Node<String> nsu = new Node<>("НГУ");
    try{
      nsu.addChild((String) null);
      Assert.fail();
    } catch (NullPointerException ignored){}
    try{
      nsu.getChild(null);
      Assert.fail();
    } catch (NullPointerException ignored){}
    try{
      nsu.deleteChild(null);
      Assert.fail();
    } catch (NullPointerException ignored){}
  }
}
