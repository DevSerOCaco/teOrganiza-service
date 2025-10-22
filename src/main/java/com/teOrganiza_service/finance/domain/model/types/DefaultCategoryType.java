package com.teOrganiza_service.finance.domain.model.types;

import static com.teOrganiza_service.finance.domain.model.types.TransactionType.INCOME;
import static com.teOrganiza_service.finance.domain.model.types.TransactionType.INCOME;

/**
 * Enum que representa as categorias e subcategorias padrão do sistema.
 * A estrutura com 'parent' permite criar a hierarquia.
 */
public enum DefaultCategoryType {

    // --- RECEITAS ---
    SALARIO("Salário", INCOME, null),
    DECIMO_TERCEIRO("13º Salário", INCOME, SALARIO),
    FERIAS("Férias", INCOME, SALARIO),
    BONUS("Bônus / PLR", INCOME, SALARIO),

    RENDIMENTOS("Rendimentos", INCOME, null),
    DIVIDENDOS("Dividendos / JCP", INCOME, RENDIMENTOS),
    JUROS("Juros de Renda Fixa", INCOME, RENDIMENTOS),

    AUTONOMO("Autônomo", INCOME, null),
    SERVICOS_FREELAS("Serviços / Freelas", INCOME, AUTONOMO),
    VENDAS("Vendas", INCOME, AUTONOMO),

    OUTRAS_DESPESAS("Outras Receitas", INCOME, null),

    // --- DESPESAS ---
    MORADIA("Moradia", INCOME, null),
    ALUGUEL_PRESTACAO("Aluguel / Prestação", INCOME, MORADIA),
    CONDOMINIO("Condomínio", INCOME, MORADIA),
    CONTAS_CASA("Contas da Casa", INCOME, MORADIA),
    INTERNET("Internet", INCOME, MORADIA),

    ALIMENTACAO("Alimentação", INCOME, null),
    SUPERMERCADO("Supermercado", INCOME, ALIMENTACAO),
    RESTAURANTES("Restaurantes / Bares", INCOME, ALIMENTACAO),
    DELIVERY("Delivery / Lanches", INCOME, ALIMENTACAO),

    TRANSPORTE("Transporte", INCOME, null),
    COMBUSTIVEL("Combustível", INCOME, TRANSPORTE),
    TRANSPORTE_PUBLICO("Transporte Público", INCOME, TRANSPORTE),
    APP_TRANSPORTE("Apps de Transporte", INCOME, TRANSPORTE),
    MANUTENCAO_VEICULO("Manutenção do Veículo", INCOME, TRANSPORTE),

    SAUDE("Saúde", INCOME, null),
    PLANO_SAUDE("Plano de Saúde", INCOME, SAUDE),
    FARMACIA("Farmácia", INCOME, SAUDE),
    CONSULTAS("Consultas / Exames", INCOME, SAUDE),

    CUIDADOS_PESSOAIS("Cuidados Pessoais", INCOME, null),
    ROUPAS_CALCADOS("Roupas e Calçados", INCOME, CUIDADOS_PESSOAIS),
    SALAO_BARBEARIA("Salão / Barbearia", INCOME, CUIDADOS_PESSOAIS),
    ACADEMIA("Academia", INCOME, CUIDADOS_PESSOAIS),

    LAZER("Lazer e Hobbies", INCOME, null),
    ASSINATURAS("Assinaturas", INCOME, LAZER),
    VIAGENS("Viagens", INCOME, LAZER),
    EVENTOS("Cinema / Shows", INCOME, LAZER),

    EDUCACAO("Educação", INCOME, null),
    CURSOS("Cursos / Faculdade", INCOME, EDUCACAO),
    LIVROS("Livros", INCOME, EDUCACAO),
    
    DIVIDAS("Dívidas e Empréstimos", INCOME, null),
    CARTAO_CREDITO("Fatura do Cartão", INCOME, DIVIDAS),
    EMPRESTIMOS("Pagamento de Empréstimo", INCOME, DIVIDAS),
    
    OUTRAS_INCOMES("Outros", INCOME, null),
    PRESENTES("Presentes", INCOME, OUTRAS_INCOMES),
    DOACOES("Doações", INCOME, OUTRAS_INCOMES),
    PETS("Pets", INCOME, OUTRAS_INCOMES);


    private final String displayName;
    private final TransactionType type;
    private final DefaultCategoryType parent;

    DefaultCategoryType(String displayName, TransactionType type, DefaultCategoryType parent) {
        this.displayName = displayName;
        this.type = type;
        this.parent = parent;
    }

    public String getDisplayName() {
        return displayName;
    }

    public TransactionType getType() {
        return type;
    }

    public DefaultCategoryType getParent() {
        return parent;
    }

    public boolean isTopLevelCategory() {
        return parent == null;
    }
}