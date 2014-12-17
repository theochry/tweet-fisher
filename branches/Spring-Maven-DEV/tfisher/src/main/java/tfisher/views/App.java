package tfisher.views;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import tfisher.entities.User;
import tfisher.session.UserModelHibernateImpl;
import tfisher.session.UserModelInterface;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
          UserModelInterface usermanager = new UserModelHibernateImpl();
          
User wanted = usermanager.findByUserName("fiontor");
 System.out.println( wanted.getIdStr());
ApplicationContext context = new FileSystemXmlApplicationContext("/resources/app-config.xml");
        User user = (User)context.getBean("user");
        
      
    }
}
