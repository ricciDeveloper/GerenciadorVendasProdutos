async function createProduct() {
    const name = prompt("Informe o nome do produto:");
    if(!name) return;

    const value_buy = parseFloat(prompt("Informe o valor de compra:"));
    if(isNaN(value_buy)) return;

    const value_sell = parseFloat(prompt("Informe o valor de venda:"));
    if(isNaN(value_sell)) return;

    const quantity = parseInt(prompt("Informe a quantidade:"));
    if(isNaN(quantity)) return;

    const percent = parseInt(prompt("Informe o percentual de comissão:"));
    if(isNaN(percent)) return;

    //Criar o objeto

    const newProduct =  {
        name: name,
        value_buy: value_buy,
        value_sell: value_sell,
        quantity: quantity,
        percent: percent
    };
    try{  //Requisição POST para salvar o produto no backend
        const response = await fetch ('http://localhost:8080/products/salvarProduto', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newProduct)
        });
        if(!response.ok){
            throw new Error('Erro ao salvar o produto');
        }
        alert('Produto salvo com sucesso');

    }
    catch (error){ 
        console.error('Erro na requisição:', error);
        alert('Erro ao salvar o produto');
    }
}


// Função para buscar todos os produtos
async function fetchAllProducts() {
    try {
        const response = await fetch('http://localhost:8080/products/buscarTodosProdutos');
        if (!response.ok) {
            throw new Error('Erro ao buscar os produtos');
        }
        const products = await response.json();
        displayProducts(products); // Exibir os produtos na tela
    } catch (error) {
        console.error('Erro na requisição:', error);
    }
}

// Função para buscar um produto por ID
async function fetchProductById() {
    const id = prompt("Informe o ID do produto que deseja buscar:");
    if (!id) return;

    try {
        const response = await fetch(`http://localhost:8080/products/buscar/${id}`);
        if (!response.ok) {
            throw new Error('Produto não encontrado');
        }
        const product = await response.json();
        displayProductDetails(product); // Exibir os detalhes do produto
    } catch (error) {
        console.error('Erro na requisição:', error);
    }
}

// Função para atualizar um produto
async function updateProduct() {
    const id = prompt("Informe o ID do produto que deseja atualizar:");
    if (!id) return;

    const name = prompt("Informe o novo nome do produto:");
    const valueBuy = prompt("Informe o novo valor de compra:");
    const valueSell = prompt("Informe o novo valor de venda:");
    const quantity = prompt("Informe a nova quantidade:");
    const percent = prompt("Informe o novo percentual de comissão:");

    const updatedProduct = {
        name: name,
        value_buy: parseFloat(valueBuy),
        value_sell: parseFloat(valueSell),
        quantity: parseInt(quantity),
        percent: parseInt(percent)
    };

    try {
        const response = await fetch(`http://localhost:8080/products/atualizarProduto/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedProduct)
        });
        if (!response.ok) {
            throw new Error('Erro ao atualizar o produto');
        }
        alert('Produto atualizado com sucesso');
        fetchAllProducts(); // Atualiza a lista de produtos
    } catch (error) {
        console.error('Erro na requisição:', error);
    }
}

// Função para deletar um produto
async function deleteProduct() {
    const id = prompt("Informe o ID do produto que deseja deletar:");
    if (!id) return;

    try {
        const response = await fetch(`http://localhost:8080/products/deletar/${id}`, {
            method: 'DELETE'
        });
        if (!response.ok) {
            throw new Error('Erro ao deletar o produto');
        }
        alert('Produto deletado com sucesso');
        fetchAllProducts(); // Atualiza a lista de produtos
    } catch (error) {
        console.error('Erro na requisição:', error);
    }
}

// Função para exibir os produtos na tela
function displayProducts(products) {
    const productsList = document.getElementById('productsList');
    productsList.innerHTML = ''; // Limpar o conteúdo anterior

    // Tornar o contêiner visível
    const productsContainer = document.querySelector('.products');
    productsContainer.style.display = 'grid'; // Mostrar a lista de produtos

    products.forEach(product => {
        const productDiv = document.createElement('div');
        productDiv.classList.add('product');
        productDiv.innerHTML = `
            <h3>${product.name}</h3>
            <p>Valor de compra: R$ ${product.value_buy.toFixed(2)}</p>
            <p>Valor de venda: R$ ${product.value_sell.toFixed(2)}</p>
            <p>Quantidade: ${product.quantity}</p>
            <p>Comissão: ${product.percent}%</p>
        `;
        productsList.appendChild(productDiv);
    });
}


// Função para exibir os detalhes de um único produto
function displayProductDetails(product) {
    const productDetails = document.getElementById('productDetails');
    productDetails.innerHTML = `
        <h3>Detalhes do Produto</h3>
        <p><strong>Nome:</strong> ${product.name}</p>
        <p><strong>Valor de compra:</strong> R$ ${product.value_buy.toFixed(2)}</p>
        <p><strong>Valor de venda:</strong> R$ ${product.value_sell.toFixed(2)}</p>
        <p><strong>Quantidade:</strong> ${product.quantity}</p>
        <p><strong>Comissão:</strong> ${product.percent}%</p>
    `;
}
