package com.teOrganiza_service.finance.domain.model.types;

import static com.teOrganiza_service.finance.domain.model.types.TransactionType.DESPESA;
import static com.teOrganiza_service.finance.domain.model.types.TransactionType.RECEITA;

/**
 * Enum que representa as categorias e subcategorias padrão do sistema.
 * A estrutura com 'parent' permite criar a hierarquia.
 */
public enum DefaultCategoryType {

    // --- RECEITAS ---
    SALARIO("Salário", RECEITA, null),
    DECIMO_TERCEIRO("13º Salário", RECEITA, SALARIO),
    FERIAS("Férias", RECEITA, SALARIO),
    BONUS("Bônus / PLR", RECEITA, SALARIO),

    RENDIMENTOS("Rendimentos", RECEITA, null),
    DIVIDENDOS("Dividendos / JCP", RECEITA, RENDIMENTOS),
    JUROS("Juros de Renda Fixa", RECEITA, RENDIMENTOS),

    AUTONOMO("Autônomo", RECEITA, null),
    SERVICOS_FREELAS("Serviços / Freelas", RECEITA, AUTONOMO),
    VENDAS("Vendas", RECEITA, AUTONOMO),

    OUTRAS_RECEITAS("Outras Receitas", RECEITA, null),

    // --- DESPESAS ---
    MORADIA("Moradia", DESPESA, null),
    ALUGUEL_PRESTACAO("Aluguel / Prestação", DESPESA, MORADIA),
    CONDOMINIO("Condomínio", DESPESA, MORADIA),
    CONTAS_CASA("Contas da Casa", DESPESA, MORADIA),
    INTERNET("Internet", DESPESA, MORADIA),

    ALIMENTACAO("Alimentação", DESPESA, null),
    SUPERMERCADO("Supermercado", DESPESA, ALIMENTACAO),
    RESTAURANTES("Restaurantes / Bares", DESPESA, ALIMENTACAO),
    DELIVERY("Delivery / Lanches", DESPESA, ALIMENTACAO),

    TRANSPORTE("Transporte", DESPESA, null),
    COMBUSTIVEL("Combustível", DESPESA, TRANSPORTE),
    TRANSPORTE_PUBLICO("Transporte Público", DESPESA, TRANSPORTE),
    APP_TRANSPORTE("Apps de Transporte", DESPESA, TRANSPORTE),
    MANUTENCAO_VEICULO("Manutenção do Veículo", DESPESA, TRANSPORTE),

    SAUDE("Saúde", DESPESA, null),
    PLANO_SAUDE("Plano de Saúde", DESPESA, SAUDE),
    FARMACIA("Farmácia", DESPESA, SAUDE),
    CONSULTAS("Consultas / Exames", DESPESA, SAUDE),

    CUIDADOS_PESSOAIS("Cuidados Pessoais", DESPESA, null),
    ROUPAS_CALCADOS("Roupas e Calçados", DESPESA, CUIDADOS_PESSOAIS),
    SALAO_BARBEARIA("Salão / Barbearia", DESPESA, CUIDADOS_PESSOAIS),
    ACADEMIA("Academia", DESPESA, CUIDADOS_PESSOAIS),

    LAZER("Lazer e Hobbies", DESPESA, null),
    ASSINATURAS("Assinaturas", DESPESA, LAZER),
    VIAGENS("Viagens", DESPESA, LAZER),
    EVENTOS("Cinema / Shows", DESPESA, LAZER),

    EDUCACAO("Educação", DESPESA, null),
    CURSOS("Cursos / Faculdade", DESPESA, EDUCACAO),
    LIVROS("Livros", DESPESA, EDUCACAO),
    
    DIVIDAS("Dívidas e Empréstimos", DESPESA, null),
    CARTAO_CREDITO("Fatura do Cartão", DESPESA, DIVIDAS),
    EMPRESTIMOS("Pagamento de Empréstimo", DESPESA, DIVIDAS),
    
    OUTRAS_DESPESAS("Outros", DESPESA, null),
    PRESENTES("Presentes", DESPESA, OUTRAS_DESPESAS),
    DOACOES("Doações", DESPESA, OUTRAS_DESPESAS),
    PETS("Pets", DESPESA, OUTRAS_DESPESAS);


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