package marcus.com.br.b2wtest.helper


fun Double.toBRL(): String {
    return "%.2f".format(this).replace(".", ",")
}
