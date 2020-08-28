package br.com.eliascoelho911.webscrapingdicionarioinformal.repository

import br.com.eliascoelho911.webscrapingdicionarioinformal.model.Definicao
import org.jsoup.Jsoup
import org.springframework.stereotype.Repository

@Repository
class DefinicaoRepository {
    fun buscaDefinicao(valor: String): List<Definicao> {
        val connection = Jsoup.connect("https://www.dicionarioinformal.com.br/$valor")
        val body = connection.get().body()
        val definicoesElement =
                body.select("div#main-feed > div[itemtype=http://schema.org/CreativeWork]")
        return definicoesElement.map { definicao ->
            val palavra =
                    definicao.select("div.card-header > h3 > a > span")[1]
                            .text()
            val autor =
                    definicao.select("div.card-header > small")[1]
                            .select("a > span > span")
                            .text()
            val definicaoDaPalavra =
                    definicao.select("div[itemprop=description] > p").text()
            val exemplos = definicao.select("div[itemprop=description] > blockquote").text()
            Definicao(
                    palavra = palavra,
                    definicaoDaPalavra = definicaoDaPalavra,
                    autor = autor,
                    exemplos = exemplos
            )
        }.toList()
    }
}