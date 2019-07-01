package jonahshader.environment


fun generateTerrain(terrainLayer: Layer) {
    var noise1 = generateNoiseArray(terrainLayer.width)

    for (i in 0..1) {
        noise1 = blurArray(noise1, 3)
        noise1 = blurArray(noise1, 2)
        noise1 = blurArray(noise1, 1)
        noise1 = normalizeArray(noise1)
    }

    noise1.indices.forEach {
        noise1[it] *= 0.375
        noise1[it] += 0.25
    }



    // reset layer to zero
    for (x in 0 until terrainLayer.width) {
        for (y in 0 until terrainLayer.height) {
            terrainLayer.writeSpace(x, y, false)
        }
    }

    // set layer
    for (i in noise1.indices) {
        for (j in 0 until (noise1[i] * terrainLayer.height).toInt()) {
            terrainLayer.writeSpace(i, j, true)
        }
    }
}

private fun generateNoiseArray(size: Int) : Array<Double> {
    var noiseArray = arrayOf<Double>()

    for (i in 0 until size)
        noiseArray += Math.random()

    return noiseArray
}

private fun blurArray(array: Array<Double>, sampleDistance: Int) : Array<Double> {
    var blurredArray = arrayOf<Double>()
    for (i in 0 until array.size) {
        blurredArray += (array[wrapAround(i - sampleDistance, array.size)] +
                array[wrapAround(i, array.size)] +
                array[wrapAround(i + sampleDistance, array.size)]) / 3.0
    }

    return blurredArray
}

private fun normalizeArray(array: Array<Double>) : Array<Double> {
    var maxValue = array[0]
    var minValue = array[0]

    // find largest value
    for (i in 0 until array.size)
        if (array[i] > maxValue)
            maxValue = array[i]

    // find the smallest value
    for (i in 0 until array.size)
        if (array[i] < minValue)
            minValue = array[i]

    var normalizedArray = arrayOf<Double>()
    for (i in 0 until array.size)
        normalizedArray += map(array[i], minValue, maxValue, 0.0, 1.0)

    return normalizedArray
}

// TODO: possible to optimize with modulus probably
private fun wrapAround(value: Int, threshold: Int) : Int {
    var output = value
    output = output.rem(threshold)
    if (output < 0) output += threshold

    return output
}

private fun map(value: Double, start1: Double, stop1: Double, start2: Double, stop2: Double) : Double =
    start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1))