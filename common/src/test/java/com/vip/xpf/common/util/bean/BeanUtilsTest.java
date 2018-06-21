package com.vip.xpf.common.util.bean;

import com.vip.xpf.common.util.bean.orika.annotation.FieldMap;
import com.vip.xpf.common.util.bean.orika.annotation.FieldMaps;
import lombok.Data;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * BeanUtils Tester.
 *
 * @author roman.luo
 * @since
 *     <pre>六月 21, 2018</pre>
 *
 * @version 1.0
 */
public class BeanUtilsTest {

  @Before
  public void before() throws Exception {}

  @After
  public void after() throws Exception {}

  /** Method: map(S orig, Class<D> destClass) */
  @Test
  public void testMap() throws Exception {
    User user = new User();
    user.setId(1l);
    user.setName("张三");
    System.out.println(BeanUtils.map(user, UserA.class));
    UserB userc = new UserB();
    userc.setId(2l);
    userc.setNameB("李四");
    System.out.println(BeanUtils.map(userc, UserA.class));
  }

  /** Method: copyProperties(S orig, D dest) */
  @Test
  public void testCopyProperties() throws Exception {
    // TODO: Test goes here...
  }

  /** Method: describe(S orig) */
  @Test
  public void testDescribe() throws Exception {
    // TODO: Test goes here...
  }

  /** Method: mapAsList(Iterable<S> origs, Class<D> destClass) */
  @Test
  public void testMapAsList() throws Exception {
    // TODO: Test goes here...
  }

  /** Method: mapToCollection(Iterable<S> origs, Class<D> destClass, Collection<D> collection) */
  @Test
  public void testMapToCollection() throws Exception {
    // TODO: Test goes here...
  }

  /** Method: registerClassMapperByAnnotation(Class<?> descClass) */
  @Test
  public void testRegisterClassMapperByAnnotation() throws Exception {
    // TODO: Test goes here...
  }

  /** Method: getInstance() */
  @Test
  public void testGetInstance() throws Exception {
    // TODO: Test goes here...
    /*
    try {
       Method method = BeanUtils.getClass().getMethod("getInstance");
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
  }

  @Data
  public static class UserA {

    private Long id;

    @FieldMaps({
      @FieldMap(origClass = User.class, origField = "name"),
      @FieldMap(origClass = UserB.class, origField = "nameB")
    })
    private String nameA;

    @FieldMap(origClass = User.class, origField = "name")
    private String nameB;
  }

  @Data
  public static class User {

    private Long id;
    private String name;
  }

  @Data
  public static class UserB {

    private Long id;
    private String nameB;
  }
}
