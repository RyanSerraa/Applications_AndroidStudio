package com.example.motivation.data

import com.example.motivation.MotivationConstants

import kotlin.random.Random

data class Phrase(val description: String, val category: Int)

class Mock {

    private val all = MotivationConstants.filter.ALL
    private val ac = MotivationConstants.filter.AC
    private val add = MotivationConstants.filter.ADD

    private val listPhrases: List<Phrase> = listOf(
        Phrase("Não sabendo que era impossível, foi lá e fez.", ac),
        Phrase("Você não é derrotado quando perde, você é derrotado quando desiste!", ac),
        Phrase("Quando está mais escuro, vemos mais estrelas!", ac),
        Phrase("Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente.", ac),
        Phrase("Não pare quando estiver cansado, pare quando tiver terminado.", ac),
        Phrase("O que você pode fazer agora que tem o maior impacto sobre o seu sucesso?", ac),
        Phrase("A melhor maneira de prever o futuro é inventá-lo.", add),
        Phrase("Você perde todas as chances que você não aproveita.", add),
        Phrase("Fracasso é o condimento que dá sabor ao sucesso.", add),
        Phrase(" Enquanto não estivermos comprometidos, haverá hesitação!", add),
        Phrase("Se você não sabe onde quer ir, qualquer caminho serve.", add),
        Phrase("Se você acredita, faz toda a diferença.", add),
        Phrase("Riscos devem ser corridos, porque o maior perigo é não arriscar nada!", add)
    )

    // Obtém frase aleatória de acordo com o filtro
    fun getPhrase(value: Int): String {
        val filtered = listPhrases.filter { (it.category == value || value == all) }

        // Número aleatório de 0 ao tamanho da lista retornada do filtro
        val rand = Random.nextInt(filtered.size)

        // Retorna string
        return filtered[rand].description
    }

}
