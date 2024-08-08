package desafio.stepdefinitions;

import desafio.pageobjects.GroceryCrudPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FrontendStep {

    WebDriver driver;
    GroceryCrudPage page;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void downWebDrive() {
        driver.quit();
    }

    @Given("que o usuário acessar a página {string}")
    public void queOUsuarioAcessarAPagina(String urlGroceryCrud) {
        driver.get(urlGroceryCrud);
        page = new GroceryCrudPage(driver);
    }

    @Given("mudar o valor da combo Select version para {string}")
    public void mudarOValorDaComboSelectVersionParaBootstrapV4Theme(String bootstrapVersion) {
        page.selectBootstrapVersion(bootstrapVersion);
    }

    @When("clicar no botão adicionar Customer")
    public void clicarNoBotaoAdicionarCustomer() {
        page.clickButtonAddCustomer();
    }

    @When("preencher os campos do formulário com dados fakes")
    public void preencherOsCamposDoFormularioComDadosFakes(DataTable table) {
        var row = table.asMaps().getFirst();

        page.sendKeysNameCustomer(row.get("name"));
        page.sendKeysLastNameCustomer(row.get("lastName"));
        page.sendKeysContactFirstName(row.get("contactFirstName"));
        page.sendKeysPhone(row.get("phone"));
        page.sendKeysAddressLineOne(row.get("addressLineOne"));
        page.sendKeysAddressLineTwo(row.get("addressLineTwo"));
        page.sendKeysCity(row.get("city"));
        page.sendKeysState(row.get("state"));
        page.sendKeysPostalCode(row.get("postalCode"));
        page.sendKeysCountry(row.get("country"));
        page.sendKeysSalesRepEmployeeNumber(row.get("fromEmployeer"));
        page.sendKeysCreditLimit(row.get("creditLimit"));
        page.sendKeysDeleted(row.get("deleted"));
    }

    @When("clicar no botão Save")
    public void clicarNoBotaoSave() {
        page.clickButtonSave();
    }

    @Then("validar a mensagem {string} através de uma asserção")
    public void validarAMensagemYourDataHasBeenSuccessfullyStoredIntoTheDatabaseEditCustomerOrGoBackToListAtravesDeUmaAssercao(String messageSuccess) {
        page.existMessageSuccess(messageSuccess);
    }

    @Then("fechar o browser")
    public void fecharOBrowser() {
        driver.close();
    }

    @When("pesquisar o cliente cadastrado")
    public void pesquisarOClienteCadastrado(DataTable table) {
        var row = table.asMaps().getFirst();
        page.sendKeysSearchNameCustomer(row.get("customer"));
    }

    @When("clicar no checkbox abaixo da palavra Actions")
    public void clicar_no_checkbox_abaixo_da_palavra_actions() {
        page.clickCheckbox();
    }

    @When("clicar no botão Delete")
    public void clicarNoBotaoDelete() {
        page.clickButtonDelete();
    }

    @When("validar o texto {string}")
    public void validarOTexto(String messagePopDelete) {
        page.assertMesssagePopDelete(messagePopDelete);
    }

    @When("confirmar opção Delete")
    public void confirmarOpcaoDelete() {
        page.clickButtonMultipleConfirmDelete();
    }

    @Then("validar mensagem da exclusão {string}")
    public void validarMensagemDaExclusao(String messageConfirmDelete) {
        page.assertMessageConfirmDelete(messageConfirmDelete);
    }
}
