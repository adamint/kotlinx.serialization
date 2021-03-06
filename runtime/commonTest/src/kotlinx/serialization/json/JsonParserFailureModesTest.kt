/*
 * Copyright 2017-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

package kotlinx.serialization.json

import kotlinx.serialization.*
import kotlin.test.*

class JsonParserFailureModesTest : JsonTestBase() {

    @Serializable
    data class Holder(
        val id: Long
    )

    @Test
    fun testFailureModes() = parametrizedTest {
        assertFailsWith<JsonDecodingException> { default.parse(Holder.serializer(), """{"id": "}""", it) }
        assertFailsWith<JsonDecodingException> { default.parse(Holder.serializer(), """{"id": ""}""", it) }
        assertFailsWith<JsonDecodingException> { default.parse(Holder.serializer(), """{"id":a}""", it) }
        assertFailsWith<JsonDecodingException> { default.parse(Holder.serializer(), """{"id":2.0}""", it) }
        assertFailsWith<JsonDecodingException> { default.parse(Holder.serializer(), """{"id2":2}""", it) }
        assertFailsWith<JsonDecodingException> { default.parse(Holder.serializer(), """{"id"}""", it) }
        assertFailsWith<JsonDecodingException> { default.parse(Holder.serializer(), """{"id}""", it) }
        assertFailsWith<JsonDecodingException> { default.parse(Holder.serializer(), """{"i}""", it) }
        assertFailsWith<JsonDecodingException> { default.parse(Holder.serializer(), """{"}""", it) }
        assertFailsWith<MissingFieldException> { default.parse(Holder.serializer(), """{}""", it) }
        assertFailsWith<JsonDecodingException> { default.parse(Holder.serializer(), """{""", it) }
        assertFailsWith<JsonDecodingException> { default.parse(Holder.serializer(), """}""", it) }
        assertFailsWith<JsonDecodingException> { default.parse(Holder.serializer(), """{""", it) }
    }
}
