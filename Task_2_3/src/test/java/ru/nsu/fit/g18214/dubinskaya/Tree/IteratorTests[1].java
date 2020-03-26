package ru.nsu.fit.g18214.dubinskaya.Tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorTests {
  @Test
  public void iteratorBFSTest() {
    Tree<String> tree = new Tree<>("Ла-ла-ла");
    tree.setIterationType(Tree.BFS);
    tree.addChild("лф-ла");
    tree.addChild("соолнышко","лф-ла");
    tree.addChild("соолнышко2","лф-ла");
    Iterator<String> iter = tree.iterator();
    Assert.assertEquals("Ла-ла-ла", iter.next());
    Assert.assertEquals("лф-ла", iter.next());
    Assert.assertEquals("соолнышко", iter.next());
    Assert.assertEquals("соолнышко2", iter.next());
    Assert.assertFalse(iter.hasNext());
  }

  @Test
  public void catchAdding() {
    Tree<String> rel = new Tree<>("Root");
    rel.addChild("level1", "Root");
    rel.addChild("level2", "Root", "level1");
    rel.setIterationType(Tree.BFS);
    Iterator<String> iter = rel.iterator();
    Assert.assertEquals("Root", iter.next());
    rel.addChild("SeregaLOh", "Root", "level1", "level2");
    try {
      iter.next();
      Assert.fail();
    } catch (ConcurrentModificationException ignored) {}
  }

  @Test
  public void outOfBound(){
    Tree<String> nsu = new Tree<>("НГУ");
    nsu.addChild("НГУ", "ММФ");
    nsu.addChild("НГУ", "ФФ");
    nsu.addChild("НГУ", "ФИТ");
    nsu.addChild("НГУ", "ГГФ");
    nsu.addChild("ФИТ", "СИ");
    nsu.addChild("ФИТ", "КОИ");
    nsu.setIterationType(Tree.BFS);
    Iterator<String> iter = nsu.iterator();
    while(iter.hasNext())
      Assert.assertNotNull(iter.next());
    try {
      iter.next();
      Assert.fail();
    } catch (NoSuchElementException ignored) {}
  }
}
