package demo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
        ChromeDriver driver;
        GoogleFormWrapper wrapper;
    
        public TestCases() {
            System.out.println("Constructor: TestCases");
            //WebDriverManager.chromedriver().timeout(30).setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            wrapper = new GoogleFormWrapper(driver);
        }
    
        public void endTest() {
            System.out.println("End Test: TestCases");
            driver.close();
            driver.quit();
        }
    
        public void GoogleFormAutomation() throws InterruptedException {
            System.out.println("Start Test case: GoogleFormAutomation");
            driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");
    
            Thread.sleep(5000);
    
            wrapper.sendKeys(By.xpath("(//div[@class='Qr7Oae'])[1]//input[@type='text']"), "Naveen Subramanian");
            System.out.println("Step 01: Name - Completed");

            long epoch = System.currentTimeMillis() / 1000;
            wrapper.sendKeys(By.xpath("(//div[@class='Qr7Oae'])[2]//textarea"), "I want to be the best QA Engineer! " + epoch);
            System.out.println("Step 02: Why are you practicing Automation? - Completed");
    
            wrapper.clickElement(By.xpath("(//div[@class='AB7Lab Id5V1'])[2]"));
            System.out.println("Step 03: How much experience do you have in Automation Testing? - Completed");
    
            wrapper.clickElement(By.xpath("(//div[@class='uHMk6b fsHoPb'])[1]"));
            wrapper.clickElement(By.xpath("(//div[@class='uHMk6b fsHoPb'])[2]"));
            wrapper.clickElement(By.xpath("(//div[@class='uHMk6b fsHoPb'])[4]"));
            System.out.println("Step 04: Which of the following have you learned in Crio.Do for Automation Testing? - Completed");
    
            wrapper.clickElement(By.xpath("(//div[@class='Qr7Oae'])[5]//div[@role='listbox']"));
            Thread.sleep(2000);
            wrapper.clickElement(By.xpath("(//div[@class='Qr7Oae'])[5]//div[@role='listbox']/div[2]//div[@data-value='Mr']"));
            System.out.println("Step 05: How should you be addressed? - Completed");
    
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String txtDateText = currentDate.minusDays(7).format(formatter);
            wrapper.sendKeys(By.xpath("(//div[@class='Qr7Oae'])[6]//input[@type='date']"), txtDateText);
            System.out.println("Step 06: What was the date 7 days ago? - Completed");
    
            LocalTime currentTime = LocalTime.now();
            int hr = currentTime.getHour();
            int min = currentTime.getMinute();
            if (hr == 0) {
                hr = 12;
            } else if (hr > 12) {
                hr -= 12;
            }

            String hour = String.format("%02d", hr);
            String minute = String.format("%02d", min);
    
            List<WebElement> timeElements = wrapper.findElements(By.xpath("(//div[@class='Qr7Oae'])[7]//input"));
            timeElements.get(0).sendKeys(hour);
            timeElements.get(1).sendKeys(minute);
            System.out.println("Step 07: What is the time right now? - Completed");
        
            driver.get("https://www.amazon.in/");  
            wrapper.dismissAlert();
    
            wrapper.clickElement(By.xpath("//span[text()='Submit']"));
    
            String txtSuccessMsg = wrapper.getElementText(By.xpath("//div[@class='vHW8K']"));
            System.out.println(txtSuccessMsg);
            System.out.println("Google Form Submitted Successfully");
    
            System.out.println("End Test case: GoogleFormAutomation");
        }
}
