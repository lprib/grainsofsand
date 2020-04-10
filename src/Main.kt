import processing.core.PApplet
import processing.core.PImage

typealias SandPile = Array<IntArray>

fun main(args: Array<String>) {
    PApplet.main("Main")
}

class Main : PApplet() {
    val w = 256
    val h = 256
    val s: SandPile = Array(w) { IntArray(h) { 0 } }
    val i = PImage(w, h)

    val colors = intArrayOf(color(0), color(0, 0, 255), color(0, 255, 0), color(255, 0, 0))

    override fun settings() {
        size(600, 600)
        noSmooth()
    }

    override fun setup() {
        s[128][128] = 120000
//        s[64][64] = 10000
//        for(i in 0..200000) topple(s)
        print("test")
        updateImage(i, s)
    }

    override fun draw() {
        topple(s)
        updateImage(i, s)
        image(i, 0f, 0f, width.toFloat(), height.toFloat())
    }

    //TODO could double buffer for better animation
    fun topple(s: SandPile) {
        for (x in s.indices) {
            for (y in s[0].indices) {
                if (s[x][y] > 3) {
                    s[x][y] -= 4
                    if (x > 0) {
                        s[x - 1][y] += 1
                    }
                    if (x < s.size - 1) {
                        s[x + 1][y] += 1
                    }
                    if (y > 0) {
                        s[x][y - 1] += 1
                    }
                    if (y < s[0].size - 1) {
                        s[x][y + 1] += 1
                    }
                }
            }
        }
    }

    fun updateImage(p: PImage, s: SandPile) {
        p.loadPixels()
        for (x in s.indices) {
            for (y in s[0].indices) {
                p.pixels[y + w * x] = colors[if (s[x][y] < 4) s[x][y] else 3]
            }
        }
        p.updatePixels()
    }

//    override fun keyPressed() {
//        topple(s)
//        updateImage(i, s)
//    }
}

