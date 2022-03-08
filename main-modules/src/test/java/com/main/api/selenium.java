//package com.spring.api;
//
//import com.google.common.collect.Iterators;
//import com.spring.helper.CustomFileWriter;
//import com.spring.helper.Helper;
//import org.json.JSONObject;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.devtools.DevTools;
//import org.openqa.selenium.devtools.v85.network.Network;
//import org.openqa.selenium.devtools.v85.network.model.Response;
//import org.openqa.selenium.logging.LogEntries;
//import org.openqa.selenium.logging.LogEntry;
//import org.openqa.selenium.logging.LogType;
//import org.openqa.selenium.logging.LoggingPreferences;
//import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.time.Duration;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Optional;
//import java.util.logging.Level;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//
//public class selenium {
//    Logger logger = LoggerFactory.getLogger(selenium.class);
//
//
//    @BeforeEach
//    public void intializeWebDriver(){
//        System.setProperty("webdriver.chrome.driver","D:\\webdriver\\chromedriver.exe");
//    }
//
//    @Test
//    public void getSystemProperties(){
////        Helper.popUpConsole();
//        CustomFileWriter fw = Helper.openFileWriter("D:\\api.txt");
//        fw.writeAndFlush("Ffodsifja\n");
//        fw.writeAndFlush("DM");
//        fw.close();
//        logger.info("The system properties is: " + System.getProperties().toString());
//    }
//
//
//
//    @Test
//    public void openConsole(){
//    }
//
//    @Test
//    public void NewProcess(){
//        ProcessBuilder pb = new ProcessBuilder("D:\\run.bat");
//        try {
//            Process p = pb.start();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//
//    @Test
//    public void viblo(){
//        String url = "https://viblo.asia/api/posts";
//
//        ChromeDriver driver = new ChromeDriver();
//        CustomFileWriter fw = null;
//        for (int i = 1; i <= 1653;i++){
//
//            fw = Helper.openFileWriter("D:\\viblo\\posts\\"+i+".json");
//            driver.get(url+"?page="+i);
//            WebElement doc = driver.findElement(By.tagName("pre"));
//            fw.write(doc.getText());
//            fw.close();
//        }
//    }
//
//
////    private void ()
//
//    @Test
//    public void upwork(){
//        String url = "https://www.upwork.com/nx/jobs/search/?sort=recency&payment_verified=1&location=Japan";
//        ChromeOptions options = new ChromeOptions();
//
//        LoggingPreferences logPerf = new LoggingPreferences();
//        logPerf.enable(LogType.PERFORMANCE, Level.ALL);
//        options.setCapability(CapabilityType.LOGGING_PREFS,logPerf);
//
//        ChromeDriver driver = new ChromeDriver(options);
//
//        driver.navigate().to(url);
//        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(8000));
//
////        int status = -1;
//
//        LogEntries logs = driver.manage().logs().get("performance");
//        int iterSize = Iterators.size(logs.iterator());
//
//
//        try {
//            CustomFileWriter fw = Helper.openFileWriter("D:\\api.json");
//            fw.write("{\"data\":[");
//
//            for (Iterator<LogEntry> it = logs.iterator();it.hasNext();) {
//
//                LogEntry entry = it.next();
//                JSONObject json = new JSONObject(entry.getMessage());
//                fw.write(json.toString() + ",\n");
//
//            }
//
//            fw.write("{\"size\":" + iterSize + "}]}");
//            fw.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        driver.close();
//    }
//
//
//
//
//    private void waitForPageFullyLoad(RemoteWebDriver driver){
//        ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
//            @Override
//            public Boolean apply(WebDriver webDriver) {
//                return ((JavascriptExecutor)driver).executeScript("return document.readyState").toString().equals("complete");
//            }
//        };
//
//        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(8));
//        wait.until(condition);
//    }
//
//
//
//    @Test
//    public void test1(){
//        // https://stackoverflow.com/questions/33225947/can-a-website-detect-when-you-are-using-selenium-with-chromedriver
//
//        ChromeOptions options = new ChromeOptions();
//
////        options.addArguments("user-agent=\"\"");
//        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36");
////        options.addArguments("user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.517 Safari/537.36");
//
//        ChromeDriver driver = new ChromeDriver(options);
//        driver.get("https://www.upwork.com/search/jobs/?sort=recency&payment_verified=1&location=Japan");
//        Helper.sleepFor(60000);
//        driver.quit();
//    }
//
//    @Test
//    public void devTool(){
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-blink-features=AutomationControlled");
//        options.addArguments("--disable-extensions");
////        options.add_experimental_option('useAutomationExtension', False)
////        options.add_experimental_option("excludeSwitches", ["enable-automation"])
////        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36");
//
////        options.addArguments("user-agent=\"\"");
//        ChromeDriver driver = new ChromeDriver(options);
//
//
//        CustomFileWriter responseLog = Helper.openFileWriter("D:\\response.txt");
//        CustomFileWriter responseBodyLog = Helper.openFileWriter("D:\\body.txt");
//
//        driver.get("https://www.upwork.com/nx/jobs/search/?sort=recency&payment_verified=1&location=Japan");
////        waitForPageFullyLoad(driver);
//
//
//        DevTools devTools = driver.getDevTools();
//
//        devTools.createSession();
//        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
//
////        devTools.send(Network.clearBrowserCache());
////        devTools.send(Network.setCacheDisabled(true));
//
////        devTools.addListener(Network.requestWillBeSent(), request -> {
////
////        });
//
//        devTools.addListener(Network.responseReceived(), response -> {
//
//            Response res = response.getResponse();
//            int status = res.getStatus();
//            String url = res.getUrl();
////            String type = response.getType().toJson();
////            String headers = response.getResponse().getHeaders().toString();
//            responseLog.write(status + " " + url+"\n");
//
//        });
//
//        devTools.addListener(Network.loadingFinished(), body -> {
//            String id = body.getRequestId().toString();
//            String responseBody = devTools.send(Network.getResponseBody(body.getRequestId())).getBody();
//            responseBodyLog.write(id+"\n"+responseBody + "\n\n\n");
//        });
//
//        Helper.sleepFor(20000);
////        driver.findElement(By.cssSelector("button[class='up-pagination-item up-btn up-btn-link']")).click();
//
//        Helper.sleepFor(50000);
//        devTools.send(Network.disable());
//
//
//        driver.quit();
//        responseLog.close();
//        responseBodyLog.close();
//    }
//
//
//    @Test
//    public void chromeSession(){
//        ChromeDriver driver = new ChromeDriver();
//        CustomFileWriter fw = null;
//
////        driver.get("https://google.com");
//        try {
//            fw = Helper.openFileWriter("D:\\api.txt");
////            driver.get(url);
//            driver.getTitle();
//            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(8000));
////        WebElement searchBox = driver.findElement(By.name("q"));
////        WebElement searchButton = driver.findElement(By.name("btnK"));
////
////        searchBox.sendKeys("Selenium");
////        searchButton.click();
//
//
////            WebElement test = driver.findElement(By.xpath("//*[@class='up-card-section up-card-list-section up-card-hover']"));
//            List<WebElement> listElement = driver.findElements(By.tagName("div"));
////        String temp = test.getAttribute("value");
//            System.out.println("No of element: " + listElement.stream().count());
//            for (int i = 0; i < listElement.size(); i++){
//                String text = listElement.get(i).getText();
////                System.out.println(text);
//                fw.writeAndFlush(text);
//            }
//
//
////            Helper.sleepFor(10000);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        driver.quit();
//
//        try {
//            fw.close();
//        } catch (Exception e){
//        }
//    }
//
//
//
//    @Test
//    public void requestChrome(){
//        try {
//            ChromeOptions options = new ChromeOptions();
//            LoggingPreferences logPrefs = new LoggingPreferences();
//            ChromeDriver driver = new ChromeDriver();
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void getCrediential(){
//        ChromeDriver driver = new ChromeDriver();
//        WebElement element;
//
//        System.out.println("This is the test code " + new Object(){}.getClass().getEnclosingMethod().getName());
//        driver.get("https://www.browserstack.com");
//        driver.findElement(By.xpath(".//*[@id='account']/a")).click();
//        driver.findElement(By.id("log")).sendKeys("harish@browserstack.com"); //Sending ID
//        driver.findElement(By.id("pwd")).sendKeys("harish123"); // Sending PWD
//        driver.findElement(By.id("login")).click();
//        try{
//            element = driver.findElement (By.xpath(".//*[@id='account_logout']/a"));
//        }catch (Exception e){
//        }
////        assertThat(element).isNotNull();
////        Assert.assertNotNull(element); //Checking the element presence
//        System.out.println("Test End " + new Object(){}.getClass().getEnclosingMethod().getName());
//    }
//
//    @Test
//    public void Nou(){
//        System.out.println("Ogey");
//    }
//}
