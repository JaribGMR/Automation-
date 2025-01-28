package web

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.annotation.Keyword

public class PracticaAut {
    // Definir los elementos
    String url = "https://practicetestautomation.com/practice-test-login/"
    String loginSucces = "https://practicetestautomation.com/logged-in-successfully/"
    String contact = "https://practicetestautomation.com/contact/"
    String user = "Login"
    String pass = 'Password123'
    
    @Keyword
    def openBrowser(String url) {
        WebUI.openBrowser("")
        WebUI.navigateToUrl(url)
        
        // Verificación de URL
        String currentUrl = WebUI.getUrl()
        WebUI.verifyMatch(currentUrl, url, false)
    }
    
    @Keyword
    def sendTextLogin(String user, String pass) {
        // Ingresar usuario y contraseña
        WebUI.sendKeys(findTestObject('Object Repository/Login/Page_Test Login  Practice Test Automation/input_Username_username'), user)
        WebUI.sendKeys(findTestObject('Object Repository/Login/Page_Test Login  Practice Test Automation/input_Password_password'), pass)
        
        // Hacer clic en el botón submit
        WebUI.click(findTestObject('Object Repository/Login/Page_Test Login  Practice Test Automation/button_Submit'))
    }
    
    @Keyword
    def log_out() {
        // Log out
        boolean isPresent = WebUI.verifyElementPresent(findTestObject('Object Repository/Log out/Page_Logged In Successfully  Practice Test Automation/a_Log out'), 10)
        if (isPresent) {
            WebUI.click(findTestObject('Object Repository/Log out/Page_Logged In Successfully  Practice Test Automation/a_Log out'))
        }
    }
    
    @Keyword
    def inputsContact(String name, String lastName, String mail, String sms) {
        WebUI.sendKeys(findTestObject('Object Repository/Contact/Page_Contact  Practice Test Automation  Selenium WebDriver/input__wpformsfields0first'), name)
        WebUI.sendKeys(findTestObject('Object Repository/Contact/Page_Contact  Practice Test Automation  Selenium WebDriver/input_First_wpformsfields0last'), lastName)
        WebUI.sendKeys(findTestObject('Object Repository/Contact/Page_Contact  Practice Test Automation  Selenium WebDriver/input__wpformsfields1'), mail)
        WebUI.sendKeys(findTestObject('Object Repository/Contact/Page_Contact  Practice Test Automation  Selenium WebDriver/textarea__wpformsfields2'), sms)
        
        WebUI.click(findTestObject('Object Repository/Contact/Page_Contact  Practice Test Automation  Selenium WebDriver/button_Submit'))
    }
    
    @Keyword
    def loginSuccess() {
        openBrowser(url)
        sendTextLogin(user, pass)
        
        // Verificación de login
        String currentUrl = WebUI.getUrl()
        WebUI.verifyMatch(currentUrl, loginSucces, false)
        
        // Tomar captura
        WebUI.takeScreenshot('/Reports/loginSucces.png')
        log_out()
    }
    
    @Keyword
    def loginErrado() {
        openBrowser(url)
        sendTextLogin("incorrectUser", pass)
        
        // Verificar mensaje de error
        WebUI.verifyElementText(findTestObject('Object Repository/Errado/Page_Test Login  Practice Test Automation/div_Your username is invalid'), "Your username is invalid!")
        
        // Tomar captura
        WebUI.takeScreenshot('/Reports/loginErrado.png')
    }
    
    @Keyword
    def loginErradoPass() {
        openBrowser(url)
        sendTextLogin(user, "malaPass")
        
        // Verificar mensaje de error
        WebUI.verifyElementText(findTestObject('Object Repository/Errado/Page_Test Login  Practice Test Automation/div_Your username is invalid'), "Your username is invalid!")
        
        // Tomar captura
        WebUI.takeScreenshot('/Reports/loginErradoPass.png')
    }
    
    @Keyword
    def contact() {
        openBrowser(url)
        WebUI.navigateToUrl(contact)
        inputsContact("Jefry", "Oct", "jar@gmail.com", "esto es un sms")
        
        // Tomar captura
        WebUI.takeScreenshot('/Reports/contact.png')
    }
}
