Feature: Desafio Nt Consult
  Como
  Eu
  Para

  Scenario: Validar cenário adicionar cliente
    Given que o usuário acessar a página "https://www.grocerycrud.com/v1.x/demo/bootstrap_theme"
    And mudar o valor da combo Select version para "Bootstrap V4 Theme"
    When clicar no botão adicionar Customer
    And preencher os campos do formulário com dados fakes
      | name    | lastName   | contactFirstName | phone          | addressLineOne | addressLineTwo | city         | state | postalCode | country | fromEmployeer | creditLimit | deleted |
      | Desafio | Nt Consult | Teste            | (51) 996244499 | Rua A          | Rua B          | Porto Alegre | RS    | 899240-000 | Brasil  | Bott          | R$ 5,00     | 0       |
    And clicar no botão Save
    Then validar a mensagem "Your data has been successfully stored into the database. Edit Customer or Go back to list" através de uma asserção
    And fechar o browser

  Scenario: Deletar cliente criado no cenário 1
    Given que o usuário acessar a página "https://www.grocerycrud.com/v1.x/demo/bootstrap_theme"
    And mudar o valor da combo Select version para "Bootstrap V4 Theme"
    When pesquisar o cliente cadastrado
    | customer|
    | Desafio |
    And clicar no checkbox abaixo da palavra Actions
    And clicar no botão Delete
    And validar o texto "Are you sure that you want to delete this 1 item?"
    And confirmar opção Delete
    Then validar mensagem da exclusão "Your data has been successfully deleted from the database."
    And fechar o browser