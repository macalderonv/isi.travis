package org.urjc.isi.travis;
import static org.junit.Assert.*;
import org.junit.*;
import org.urjc.isi.travis.Min;

import java.util.*;

public class MinTest
{
   private List<String> list;   // Test fixture

   @Before      // Set up - Called before every test method.
   public void setUp()
   {
      list = new ArrayList<String>();
   }

   @After      // Tear down - Called after every test method.
   public void tearDown()
   {
      list = null;  // redundant in this example!
   }

   @Test
   public void testForNullList()
   {
      list = null;
      try {
         Min.min (list);
      } catch (NullPointerException e) {
         return;
      }
      fail ("NullPointerException expected");
   }

   @Test (expected = NullPointerException.class)
   public void testForNullElement()
   {
      list.add (null);
      list.add ("cat");
      Min.min (list); 
   }

   @Test (expected = NullPointerException.class)
   public void testForSoloNullElement()
   {
      list.add (null);
      Min.min (list);
   }

   @Test (expected = ClassCastException.class)
   @SuppressWarnings ("unchecked")
   public void testMutuallyIncomparable()
   {
      List list = new ArrayList();
      list.add ("cat");
      list.add ("dog");
      list.add (1);
      Min.min (list);/*en tiempo de ejecucion dara un error*/
   }

   @Test (expected = IllegalArgumentException.class)  /* si la lista esta vacia se eleva esta excepcion */
   public void testEmptyList()
   {
      Min.min (list);
   }


   /* esto son happypaths */
   @Test
   public void testSingleElement()
   {
      list.add ("cat");
      Object obj = Min.min (list);
      assertTrue ("Single Element List", obj.equals ("cat"));
   }

   @Test
   public void testDoubleElement()
   {
      list.add ("dog");
      list.add ("cat");
      Object obj = Min.min (list);
      assertTrue ("Double Element List", obj.equals ("cat"));
   }
  /* si tuviera que hacer muchos happy paths, para no tener que hace cien metodos --> te creas tu clase de test, te creas un metodo que devuelva una coleccion de objetos que devuelva cada caso de test, y escribes el caso de test como sum== Calc.add(a,b) donde a, b y sum son variables que te has declarado en la clase (diapo21 05-juint) */
}