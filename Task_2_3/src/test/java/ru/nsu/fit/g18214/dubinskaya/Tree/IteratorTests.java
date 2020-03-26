package ru.nsu.fit.g18214.dubinskaya.Tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorTests {
  @Test
  public void iteratorBFSTest() {
    Tree<String> religion = new Tree<>("Основные религиозные направления");
    religion.setIterationType(Tree.BFS);
    Node<String> r0 = new Node<>("Мировые религии");
    Node<String> r1 = new Node<>("Христианство");
    r1.addChild("Католицизм");
    r1.addChild("Протестантизм");
    r1.addChild("Православие");
    r0.addChild(r1);
    r1 = new Node<String>("Ислам");
    r1.addChild("Суннизм");
    r1.addChild("Шиизм");
    r1.addChild("Мюридизм");
    r0.addChild(r1);
    r1 = new Node<>("Буддизм");
    r1.addChild("Хинаяна");
    r1.addChild("Махаяна");
    r1.addChild("Ламаизм");
    r0.addChild(r1);
    religion.addNode(r0);
    r0 = new Node<>("Региональные религии");
    r0.addChild("Иудаизм");
    r0.addChild("Индуизм");
    r0.addChild("Сикхизм");
    r0.addChild("Даосизм");
    r0.addChild("Конфуцианство");
    r0.addChild("Синтоизм");
    r0.addChild("Зороастризм");
    r0.addChild("Джайкизм");
    religion.addNode(r0);
    Iterator<String> iter = religion.iterator();
    Assert.assertEquals("Основные религиозные направления", iter.next());
    Assert.assertEquals("Мировые религии", iter.next());
    Assert.assertEquals("Региональные религии", iter.next());
    Assert.assertEquals("Христианство", iter.next());
    Assert.assertEquals("Ислам", iter.next());
    Assert.assertEquals("Буддизм", iter.next());
    Assert.assertEquals("Иудаизм", iter.next());
    Assert.assertEquals("Индуизм", iter.next());
    Assert.assertEquals("Сикхизм", iter.next());
    Assert.assertEquals("Даосизм", iter.next());
    Assert.assertEquals("Конфуцианство", iter.next());
    Assert.assertEquals("Синтоизм", iter.next());
    Assert.assertEquals("Зороастризм", iter.next());
    Assert.assertEquals("Джайкизм", iter.next());
    Assert.assertEquals("Католицизм", iter.next());
    Assert.assertEquals("Протестантизм", iter.next());
    Assert.assertEquals("Православие", iter.next());
    Assert.assertEquals("Суннизм", iter.next());
    Assert.assertEquals("Шиизм", iter.next());
    Assert.assertEquals("Мюридизм", iter.next());
    Assert.assertEquals("Хинаяна", iter.next());
    Assert.assertEquals("Махаяна", iter.next());
    Assert.assertEquals("Ламаизм", iter.next());
    Assert.assertFalse(iter.hasNext());
  }

  @Test
  public void iteratorDFSTest() {
    Tree<String> religion = new Tree<>("Основные религиозные направления");
    religion.setIterationType(Tree.DFS);
    Node<String> r0 = new Node<>("Мировые религии");
    Node<String> r1 = new Node<>("Христианство");
    r1.addChild("Католицизм");
    r1.addChild("Протестантизм");
    r1.addChild("Православие");
    r0.addChild(r1);
    r1 = new Node<>("Ислам");
    r1.addChild("Суннизм");
    r1.addChild("Шиизм");
    r1.addChild("Мюридизм");
    r0.addChild(r1);
    r1 = new Node<>("Буддизм");
    r1.addChild("Хинаяна");
    r1.addChild("Махаяна");
    r1.addChild("Ламаизм");
    r0.addChild(r1);
    religion.addNode(r0);
    r0 = new Node<>("Региональные религии");
    r0.addChild("Иудаизм");
    r0.addChild("Индуизм");
    r0.addChild("Сикхизм");
    r0.addChild("Даосизм");
    r0.addChild("Конфуцианство");
    r0.addChild("Синтоизм");
    r0.addChild("Зороастризм");
    r0.addChild("Джайкизм");
    religion.addNode(r0);
    Iterator<String> iter = religion.iterator();
    Assert.assertEquals("Основные религиозные направления", iter.next());
    Assert.assertEquals("Мировые религии", iter.next());
    Assert.assertEquals("Христианство", iter.next());
    Assert.assertEquals("Католицизм", iter.next());
    Assert.assertEquals("Протестантизм", iter.next());
    Assert.assertEquals("Православие", iter.next());
    Assert.assertEquals("Ислам", iter.next());
    Assert.assertEquals("Суннизм", iter.next());
    Assert.assertEquals("Шиизм", iter.next());
    Assert.assertEquals("Мюридизм", iter.next());
    Assert.assertEquals("Буддизм", iter.next());
    Assert.assertEquals("Хинаяна", iter.next());
    Assert.assertEquals("Махаяна", iter.next());
    Assert.assertEquals("Ламаизм", iter.next());
    Assert.assertEquals("Региональные религии", iter.next());
    Assert.assertEquals("Иудаизм", iter.next());
    Assert.assertEquals("Индуизм", iter.next());
    Assert.assertEquals("Сикхизм", iter.next());
    Assert.assertEquals("Даосизм", iter.next());
    Assert.assertEquals("Конфуцианство", iter.next());
    Assert.assertEquals("Синтоизм", iter.next());
    Assert.assertEquals("Зороастризм", iter.next());
    Assert.assertEquals("Джайкизм", iter.next());
    Assert.assertFalse(iter.hasNext());
  }
  @Test
  public void catchAdding() {
    Tree<String> rel = new Tree<>("Root");
    rel.addChild("level1", "Root");
    rel.addChild("level2", "Root", "level1");
    Iterator<String> iter = rel.iterator();
    Assert.assertEquals("Root", iter.next());
    rel.addChild("SeregaLOh", "Root", "level1", "level2");
    try {
      iter.next();
      Assert.fail();
    } catch (ConcurrentModificationException ignored) {}
  }

  @Test
  public void catchDelete() {
    Tree<String> religion = new Tree<>("Основные религиозные направления");
    religion.setIterationType(Tree.DFS);
    Node<String> r0 = new Node<>("Мировые религии");
    Node<String> r1 = new Node<>("Христианство");
    r1.addChild("Католицизм");
    r1.addChild("Протестантизм");
    r1.addChild("Православие");
    r0.addChild(r1);
    r1 = new Node<>("Ислам");
    r1.addChild("Суннизм");
    r1.addChild("Шиизм");
    r1.addChild("Мюридизм");
    r0.addChild(r1);
    r1 = new Node<>("Буддизм");
    r1.addChild("Хинаяна");
    r1.addChild("Махаяна");
    r1.addChild("Ламаизм");
    r0.addChild(r1);
    religion.addNode(r0);
    r0 = new Node<>("Региональные религии");
    r0.addChild("Иудаизм");
    r0.addChild("Индуизм");
    r0.addChild("Сикхизм");
    r0.addChild("Даосизм");
    r0.addChild("Конфуцианство");
    r0.addChild("Синтоизм");
    r0.addChild("Зороастризм");
    r0.addChild("Джайкизм");
    religion.addNode(r0);
    Iterator<String> iter = religion.iterator();
    Assert.assertEquals("Основные религиозные направления", iter.next());
    religion.deleteChild("Христианство", "Основные религиозные направления", "Мировые религии", "Христианство");
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
