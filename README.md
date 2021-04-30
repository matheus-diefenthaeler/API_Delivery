# API-Delivery

Abaixo está a descrição e regras de negócios de como deve ser feita a API para um aplicativo de delivery.

## Estrutura de Classes do Domínio

A estrutura de classes abaixo, representa o que é esperado que seja persistido pela aplicação. Nem sempre as interfaces REST vão representar exatamente a estrutura de classes do domínio. Os tipos de dados foram suprimidos, você deve definir o tipo mais adequado para cada dado. Somente os atributos que referenciam outras classes foram explicitados.

- Endereco
    - id;
    - cep;
    - logradouro;
    - numero;
    - complemento;
    - bairro;
    - cidade;
    - estado;

- Usuario
    - id;
    - nome;
    - email;
    - senha;
    - cpf;
    - List\<Endereco> enderecos;

- Estabelecimento
    - id;
    -  nomeFantasia;
    -  razaoSocial;
    -  cnpj;
    -  List\<HorarioFuncionamento> horarioFuncionamento;
    -  List\<FormaPagamento> formasPagamento;
    -  List\<Endereco> enderecos;

- HorarioFuncionamento
    -  DayOfWeek diaSemana;
    -  horarioAbertura;
    -  horarioFechamento;

- Entregador
    - id;
    - nome;
    - cpf;
    - placaVeiculo;
    - telefone;
    - disponivel;

- Produto
    - id;
    - titulo;
    - descricao;
    - valor;
    - urlFoto;
    - Categoria categoria;
    - tempoPreparo;
    - Estabelecimento estabelecimento;

- Pedido
    - id;
    - Usuario usuarioSolicitante;
    - Endereco enderecoEntrega;
    - Estabelecimento estabelecimento;
    - List\<ItemPedido> itensPedido;
    - FormaPagamento formaPagamento;
    - StatusPedido status;
    - horarioSolicitacao;
    - horarioSaiuParaEntrega;
    - horarioEntrega;
    - valorTotal;
    - Entregador entregador;

- ItemPedido
    - id;
    - Produto produto;
    - quantidade;

## Funcionalidades

### 1. Usuários

#### 1.1. Cadastro de Usuários

- `POST /usuarios`

- Parâmetros de Entrada
    * Usuário
        * Nome*
        * E-Mail*
        * Senha*
        * CPF*
        * Endereços
            * CEP
            * Logradouro
            * Número
            * Complemento
            * Bairro
            * Cidade
            * Estado

- Regras e Comportamentos
    * Os atributos marcados com um "*" são obrigatórios
    * O campo CPF deve ser representado como uma String
    * Não deve ser possível cadastrar mais de um usuário com o mesmo E-Mail
        * Caso já exista outro usuário com o e-mail informado, deve lançar uma exceção que retorne o status 400 e uma mensagem informando o problema
    * Não deve ser possível cadastrar mais de um usuário com o mesmo CPF
        * Caso já exista outro usuário com o CPF informado, deve lançar uma exceção que retorne o status 400 e uma mensagem informando o problema
    * O campo CEP deve aceitar somente o formato 99999-999.
    * Os estados podem ser representados por um enum
    * O campo Cidade deve ser representado como String

- Saída esperada em caso de sucesso
    * Status: `201 CREATED`
    * Body
        - Objeto Usuario contendo todos os campos cadastrados

#### 1.2. Listagem de Usuários

- `GET /usuarios`

- Parâmetros de Entrada
    * N/A

- Regras e Comportamentos
    * O campo senha não deve ser retornado

- Desafio
    * Listar os usuários com paginação, em ordem alfabética pelo nome por padrão

- Saída esperada em caso de sucesso
    * Status: `200 SUCCESS`
    * Body
        - List\<Usuario>
            * Usuário
                * Nome
                * E-Mail
                * CPF
                * Endereços
                    * CEP
                    * Logradouro
                    * Número
                    * Complemento
                    * Bairro
                    * Cidade
                    * Estado

#### 1.3. Buscar usuário por Id

- `GET /usuarios/{id}`

- Parâmetros de Entrada
    * id

- Regras e Comportamentos
    * A aplicação deve obter o usuário através do Id informado.
        * Caso nenhum usuário seja localizado, deve lançar uma exceção que retorne o status 404
    * O campo senha não deve ser retornado

- Saída esperada em caso de sucesso
    * Status: `200 SUCCESS`
        - Usuário
            * Nome
            * E-Mail
            * CPF
            * Endereço
                * CEP
                * Logradouro
                * Número
                * Complemento
                * Bairro
                * Cidade
                * Estado

#### 1.4. Adicionar Endereço para um Usuário

- `POST /usuarios/{id}/enderecos`

- Parâmetros de Entrada
    * Endereço
        * CEP*
        * Logradouro*
        * Número*
        * Complemento
        * Bairro*
        * Cidade*
        * Estado*

- Regras e Comportamentos
    * Os atributos marcados com um "*" são obrigatórios
    * Os endereços anteriores devem ser mantidos
    * A aplicação deve obter o usuario através do Id informado.
        * Caso nenhum usuario seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema
    * O campo CEP deve aceitar somente o formato 99999-999.
    * Os estados podem ser representados por um enum
    * O campo Cidade deve ser representado como String

- Saída esperada em caso de sucesso
    * Status: `201 CREATED`

#### 1.5. Remover o Endereço de um Usuário

- `DELETE /usuarios/{id}/enderecos/{idEndereco}`

- Parâmetros de Entrada
    * Id do Usuário
    * Id do Endereço

- Regras e Comportamentos
    * Não deve ser possível excluir um endereço que não seja do usuário informado
    * A aplicação deve obter o usuario através do Id informado.
        * Caso nenhum usuario seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema
    * A aplicação deve obter o endereço através do Id informado.
        * Caso nenhum endereço seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema

- Saída esperada em caso de sucesso
    * Status: `200 SUCCESS`

#### 1.6. Alterar um Usuário

- `PUT /usuarios/{id}`

- Parâmetros de Entrada
    * Usuário
        * Nome*
        * E-Mail*
        * Senha*
        * Endereços
            * CEP
            * Logradouro
            * Número
            * Complemento
            * Bairro
            * Cidade
            * Estado

- Regras e Comportamentos
    * Os atributos marcados com um "*" são obrigatórios
    * Não deve ser possível alterar o CPF de um usuário já cadastrado
    * A aplicação deve obter o usuario através do Id informado.
        * Caso nenhum usuario seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema
    * Não deve ser possível cadastrar mais de um usuário com o mesmo E-Mail
        * Caso já exista outro usuário com o e-mail informado, deve lançar uma exceção que retorne o status 400 e uma mensagem informando o problema
    * O campo CEP deve aceitar somente o formato 99999-999.
    * Os campos Cidade e Estado devem ser representados como Strings

- Saída esperada em caso de sucesso
    * Status: `200 SUCCESS`
    * Body
        - Objeto Usuario contendo todos os campos

### 2. Estabelecimento

#### 2.1. Cadastro de Estabelecimento

- `POST /estabelecimentos`

- Parâmetros de Entrada
    * Estabelecimento
        * Nome Fantasia*
        * Razão Social*
        * CNPJ*
        * Horários de Funcionamento*
            * Dia da Semana*
            * Horário Abertura*
            * Horário Fechamento*
        * Formas de Pagamento*
        * Endereços
            * CEP
            * Logradouro
            * Número
            * Complemento
            * Bairro
            * Cidade
            * Estado

- Regras e Comportamentos
    * Os atributos marcados com um "*" são obrigatórios
    * Não deve ser possível cadastrar mais de um estabelecimento com o mesmo CNPJ
        * Caso já exista outro estabelecimento com o CNPJ informado, deve lançar uma exceção que retorne o status 400 e uma mensagem informando o problema
    * O campo CNPJ deve ser representado como uma String
    * O horário de funcionamento deve permitir a inclusão de várias configurações por dia da semana. Ex:
        * SEGUNDA das 08:00 as 12:00
        * SEGUNDA das 14:00 as 18:00
        * TERÇA das 08:00 as 18:00
    * O campo CEP deve aceitar somente o formato 99999-999.
    * Os estados podem ser representados por um enum
    * O campo Cidade deve ser representado como String
    * Os únicos valores possíveis para formas de pagamento são:
        * Cartão de Crédito
        * Cartão de Débito
        * Vale Refeição
        * Pix
        * Dinheiro

- Saída esperada em caso de sucesso
    * Status: `201 CREATED`
    * Body
        - Objeto Estabelecimento contendo todos os campos cadastrados

#### 2.2. Listagem de Estabelecimento

- `GET /estabelecimentos`

- Parâmetros de Entrada
    * N/A

- Desafio
    * Listar os estabelecimentos com paginação, em ordem alfabética pelo nomeFantasia por padrão

- Saída esperada em caso de sucesso
    * Status: `200 SUCCESS`
    * Body:
        - Lista de Estabelecimentos
            * É esperado que sejam exibidos todos os atributos do Estabelecimentos, bem como os atributos de Endereço.

#### 2.3. Buscar estabelecimento por Id

- `GET /estabelecimentos/{id}`

- Parâmetros de Entrada
    * cnpj

- Regras e Comportamentos
    * A aplicação deve obter o estabelecimento através do Id informado.
        * Caso nenhum estabelecimento seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema

- Saída esperada em caso de sucesso
    * Status: `200 SUCCESS`
    * Body:
        - Estabelecimento
            * Nome Fantasia
            * Razão Social
            * CNPJ
            * Horários de Funcionamento
                * Dia da Semana
                * Horário Abertura
                * Horário Fechamento
            * Formas de Pagamento
            * Endereços
                * CEP
                * Logradouro
                * Número
                * Complemento
                * Bairro
                * Cidade
                * Estado

#### 2.4. Adicionar Endereço em um Estabelecimento

- `POST /estabelecimentos/{id}/enderecos`

- Parâmetros de Entrada
    * Endereço
        * CEP*
        * Logradouro*
        * Número*
        * Complemento
        * Bairro*
        * Cidade*
        * Estado*

- Regras e Comportamentos
    * Os atributos marcados com um "*" são obrigatórios
    * A aplicação deve obter o estabelecimento através do Id informado.
        * Caso nenhum estabelecimento seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema
    * Os endereços anteriores devem ser mantidos
    * O campo CEP deve aceitar somente o formato 99999-999.
    * Os estados podem ser representados por um enum
    * O campo Cidade deve ser representado como String

- Saída esperada em caso de sucesso
    * Status: `201 CREATED`

#### 2.5. Remover o Endereço de um Estabelecimento

- `DELETE /estabelecimentos/{id}/enderecos/{idEndereco}`

- Parâmetros de Entrada
    * Id do Estabelecimento
    * Id do Endereço

- Regras e Comportamentos
    * Não deve ser possível excluir um endereço que não seja do estabelecimento informado
    * A aplicação deve obter o estabelecimento através do Id informado.
        * Caso nenhum estabelecimento seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema
    * A aplicação deve obter o endereço através do Id informado.
        * Caso nenhum endereço seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema

- Saída esperada em caso de sucesso
    * Status: `200 SUCCESS`

### 3. Produtos

#### 3.1. Cadastro de Produtos

- `POST /produtos`

- Parâmetros de Entrada
    * Produto
        * Título*
        * Descrição*
        * Valor*
        * Id do Estabelecimento*
        * Categoria
        * Tempo de Preparo
        * URL Foto

- Regras e Comportamentos
    * Os atributos marcados com um "*" são obrigatórios
    * Não deve ser possível cadastrar um produto com valor inferior à 0
    * A aplicação deve obter o estabelecimento através do Id informado.
        * Caso nenhum estabelecimento seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema
        * Após o retorno, deve relacionar o Produto ao Estabelecimento
    * Os únicos valores possíveis para Categoria são:
        * Refeição
        * Mercado
    * O tempo de preparo deve ser representado como um inteiro em minutos. Caso não seja informado, deve ser admitido um valor padrão de 30 minutos.

- Saída esperada em caso de sucesso
    * Status: `201 CREATED`
    * Body:
        - Produto
            * Título
            * Descrição
            * Valor
            * CNPJ do Estabelecimento
            * Categoria
            * Tempo de Preparo
            * URL Foto

#### 3.2. Listagem de Produtos

- `GET /produtos`

- Parâmetros de Entrada
    * N/A

- Desafio
    * Listar os Produtos com paginação, em ordem alfabética pela descricao por padrão

- Saída esperada em caso de sucesso
    * Status: `200 SUCCESS`
    * Body:
        - Lista de Produtos
            * É esperado que sejam exibidos todos os atributos do Produtos, bem como os atributos de Estabelecimento.

#### 3.3. Exclusão de Produtos

- `DELETE /produtos/{id}`

- Parâmetros de Entrada
    - Id do Produto

- Regras e Comportamentos
    - O sistema deve verificar se existe um produto com este id. Caso contrário deve retornar um erro 404.

- Saída esperada em caso de sucesso
    * Status: `200 SUCCESS`

### 4. Entregadores

#### 4.1. Cadastro de Entregadores

- `POST /entregadores`

- Parâmetros de Entrada
    * Entregador
        * Nome*
        * CPF*
        * Telefone*
        * Placa do Veiculo*

- Regras e Comportamentos
    * Os atributos marcados com um "*" são obrigatórios
    * O campo CPF deve ser representado como uma String
    * Não deve ser possível cadastrar mais de um entregador com o mesmo CPF
        * Caso seja feito a tentativa de cadastrar um entregador com um CPF já cadastrado, deve lançar uma exception que retorne o status 400 e uma mensagem informando o problema
    * Não deve ser possível cadastrar um entregador com uma placa de veículo fora do padrão: 'AAA 9999' ou 'AAA 9A99'.

- Saída esperada em caso de sucesso
    * Status: `201 CREATED`
    - Body:
        * Entregador
            * Nome
            * CPF
            * Telefone
            * Placa do Veiculo

#### 4.2. Listagem de Entregadores

- `GET /entregadores`

- Parâmetros de Entrada
    * N/A

- Desafio
    * Listar os entregadores com paginação, em ordem alfabética pelo nome por padrão

- Saída esperada em caso de sucesso
    * Status: `200 SUCCESS`
    * Body:
        - List
            * Entregador
                * Nome*
                * CPF*
                * Telefone*
                * Placa do Veiculo*

### 5. Pedido

#### 5.1. Fazer pedido

- `POST /pedidos`

- Parâmetros de Entrada
    * Id do Estabelecimento
    * Id do Usuário Solicitante
    * Id do Endereço de Entrega
    * Lista de Items
        * Identificador Produto
        * Quantidade
    * Forma de Pagamento

- Regras e Comportamentos
    * A aplicação deve obter o estabelecimento através do Id informado.
        * Caso nenhum estabelecimento seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema
    * A aplicação deve obter o usuario através do Id informado.
        * Caso nenhum usuario seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema
    * A aplicação deve filtrar o endereço a partir dos endereços do usuario através do Id informado.
        * Caso nenhum endereço seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema
    * Não deve ser possível fazer um pedido usando uma forma de pagamento não aceita pelo estabelecimento
    * Não deve ser possível fazer um pedido para um usuário que não possua endereços cadastrados
        * Caso o usuário não possua endereços, deve lançar uma exception que retorne o status 400 e uma mensagem informando o problema
    * Não deve ser possível fazer um pedido em um estabelecimento que não esteja em funcionamento no momento do pedido
        * Caso o estabelecimento esteja fechado, deve lançar uma exception que retorne o status 400 e uma mensagem informando o problema
    * Não deve ser possível fazer um pedido com itens que não sejam do estabelecimento informado
        * Caso tenha algum item que não seja do estabelecimento informado, deve lançar uma exception que retorne o status 400 e uma mensagem informando o problema
    * Não deve ser possível pedir mais do que 5 itens do mesmo produto
    * Deve ser armazenada a hora em que o pedido foi feito
    * Deve ser calculado o tempo previsto de entrega em minutos, utilizando a fórmula:
        * tempo preparo de cada item * quantidade de cada item
    * Deve ser definido o status do pedido como "Em Preparo"
    * Os status de pedido possíveis são:
        * Em preparo
        * Saiu para Entrega
        * Entregue

- Saída esperada em caso de sucesso
    * Status: `201 CREATED`
    * Body:
        - Dados do Novo Pedido
            * Identificador do Pedido
            * Tempo estimado para entrega em minutos
            * Endereço de Entrega
                * CEP
                * Logradouro
                * Número
                * Complemento
                * Bairro
                * Cidade
                * Estado
            * Valor total
            * Status

#### 5.2. Buscar um pedido

- `GET /pedidos/{id}`

- Parâmetros de Entrada
    * Identificador do Pedido

- Regras e Comportamentos
    * Deve ser calculado o horário previsto para entrega, utilizando a fórmula:
        * hora do pedido + (tempo preparo de cada item * quantidade de cada item)
    * Caso o pedido já esteja entregue, o tempo estimado não deve ser calculado, retornado null neste atributo

- Saída esperada em caso de sucesso
    * Status: `200 SUCCESS`
    * Body:
        - Informações do Pedido
            * Nome do Usuário
            * Endereço de Entrega
                * CEP
                * Logradouro
                * Número
                * Complemento
                * Bairro
                * Cidade
                * Estado
            * Nome fantasia do Estabelecimento
            * Lista de Items do Pedido
                * Título do Produto
                * Quantidade
            * Valor total do pedido
            * Entregador
                * Nome
                * CPF
                * Telefone
                * Placa
            * Horário previsto para entrega

#### 5.3. Entregar pedido para o Entregador

- `PUT /pedidos/{id}/entregar`

- Parâmetros de Entrada
    * Identificador do Pedido

- Regras e Comportamentos
    * Não deve ser possível entregar um pedido que não esteja no status "EM_PREPARO"
        * Caso o pedido esteja em outro status, o sistema deve retornar uma exceção com status 400 e uma mensagem explicando o problema
    * Deve ser armazenada a hora em que o pedido foi alterado para "Saiu para Entrega"
    * O status do pedido deve ser atualizado para "Saiu para Entrega"
    * Deve ser escolhido um Entregador disponível no sistema
        * Caso não tenha nenhum entregador disponível, deve retornar um erro informando essa situação, pedindo para tentar novamente mais tarde
        * Ao final, o status do entregador deve ser atualizado para indisponível

- Saída esperada em caso de sucesso
    * Status: `200 SUCCESS`
    * Body:
        - Dados do Entregador
            * Id
            * Nome
            * CPF
            * Telefone
            * Placa

#### 5.4. Finalizar o pedido

- `PUT /pedidos/{id}/finalizar`

- Parâmetros de Entrada
    * Identificador do Pedido

- Regras e Comportamentos
    * Não deve ser possível entregar um pedido que não esteja no status "SAIU_PARA_ENTREGA"
        * Caso o pedido esteja em outro status, o sistema deve retornar uma exceção com status 400 e uma mensagem explicando o problema
    * Deve ser armazenada a hora em que o pedido foi entregue
    * Deve ser atualizado o Entregador do pedido para disponível

- Saída esperada em caso de sucesso
    * Status: `200 SUCCESS`

#### 5.5. Cancelar o pedido

- `DELETE /pedidos/{id}`

- Parâmetros de Entrada
    * Identificador do Pedido

- Regras e Comportamentos
    * Não deve ser possível cancelar um pedido que não esteja no status "EM_PREPARO"
        * Caso o pedido esteja em outro status, o sistema deve retornar uma exceção com status 400 e uma mensagem explicando o problema
    * Só deve ser possível cancelar o pedido até 10 minutos a partir do horário do pedido
    * Deve ser armazenada a hora em que o pedido foi cancelado

- Saída esperada em caso de sucesso
    * Status: `200 SUCCESS`

---

Collection do Postman contendo exemplos de requisição e de respostas: https://www.getpostman.com/collections/25d51094076fe620904c

### Dicas

- Para acessar o Swagger, utilize o caminho: http://localhost:8080/swagger-ui/

- Para acessar o console do H2, utilize o caminho: http://localhost:8080/h2 e, no campo `JDBC URL:` informe o valor `jdbc:h2:~/tcc-h2-db` 
