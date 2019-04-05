/*
 * Copyright (C) 2016. Tien Hoang.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package base.imonitore.com.br.baseapplication.servicesmanager

import android.content.Context
import android.util.Log
import okhttp3.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URI
import java.util.*

/**
 *
 */
class MockInterceptor(private val mContext: Context) : Interceptor {

    private var mContentType = "application/json"

    /**
     * Set content type for header
     * @param contentType Content type
     * @return FakeInterceptor
     */
    fun setContentType(contentType: String): MockInterceptor {
        mContentType = contentType
        return this
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val listSuggestionFileName = ArrayList<String>()
        val method = chain.request().method().toLowerCase()

        var response: Response? = null
        // Get Request URI.
        val uri = getURI(chain)


        Log.d(TAG, "--> Request url: [" + method.toUpperCase() + "]" + uri.toString())

        val defaultFileName = getFileName(chain)

        //create file name with http method
        //eg: getLogin.json
        listSuggestionFileName.add(method + upCaseFirstLetter(defaultFileName))

        //eg: acessar.json
        listSuggestionFileName.add(defaultFileName)

        val responseFileName = getFirstFileNameExist(listSuggestionFileName, uri)
        if (responseFileName != null) {
            val fileName = getFilePath(uri, responseFileName)
            Log.d(TAG, "Read data from file: $fileName")
            try {
                val inp = mContext.assets.open(fileName)
                val r = BufferedReader(InputStreamReader(inp))
                val responseStringBuilder = StringBuilder()
                var line : String?
                do {
                    line = r.readLine()
                    if (line == null)
                        break
                    responseStringBuilder.append(line).append('\n')
                } while (true)

                Log.d(TAG, "Response: " + responseStringBuilder.toString())
                response = Response.Builder()
                        .code(200)
                        .message(responseStringBuilder.toString())
                        .request(chain.request())
                        .protocol(Protocol.HTTP_1_0)
                        .body(ResponseBody.create(MediaType.parse(mContentType), responseStringBuilder.toString().toByteArray()))
                        .addHeader("content-type", mContentType)
                        .build()
            } catch (e: IOException) {
                Log.e(TAG, e.message, e)
            }

        } else {
            for (file in listSuggestionFileName) {
                Log.e(TAG, "File not exist: " + getFilePath(uri, file))
            }
            response = chain.proceed(chain.request())
        }

        Log.d(TAG, "<-- END [" + method.toUpperCase() + "]" + uri.toString())
        return response
    }

    private fun getURI(chain: Interceptor.Chain): URI {
        if (chain.request().url().pathSize() > 1) {
            val pathSegments = StringBuilder()
            for (p in chain.request().url().pathSegments()) {
                pathSegments.append("/")
                pathSegments.append(p)
            }
            //            return URI.create("http://"+chain.request().url().host()+"/"+chain.request().url().pathSegments().get(0));
            return URI.create("http://" + chain.request().url().host() + pathSegments.toString())
        } else
            return chain.request().url().uri()
    }

    private fun upCaseFirstLetter(str: String): String {
        return str.substring(0, 1).toUpperCase() + str.substring(1)
    }

    @Throws(IOException::class)
    private fun getFirstFileNameExist(inputFileNames: List<String>, uri: URI): String? {
        var mockDataPath = uri.host + uri.path
        mockDataPath = mockDataPath.substring(0, mockDataPath.lastIndexOf('/'))
        Log.d(TAG, "Scan files in: $mockDataPath")
        //List all files in folder
        val files = mContext.assets.list(mockDataPath)
        for (fileName in inputFileNames) {
            if (fileName != null) {
                for (file in files) {
                    if (fileName == file) {
                        return fileName
                    }
                }
            }
        }
        return null
    }

    private fun getFileName(chain: Interceptor.Chain): String {
        val fileName: String
        if (chain.request().url().pathSegments().size > 1) {
            fileName = chain.request().url().pathSegments()[chain.request().url().pathSegments().size - 1]
            //            fileName = chain.request().url().pathSegments().get(0);
        } else {
            fileName = chain.request().url().pathSegments()[chain.request().url().pathSegments().size - 1]
        }
        return if (fileName.isEmpty()) "index$FILE_EXTENSION" else fileName + FILE_EXTENSION
    }

    private fun getFilePath(uri: URI, fileName: String): String {
        val path: String
        if (uri.path.lastIndexOf('/') != uri.path.length - 1) {
            path = uri.path.substring(0, uri.path.lastIndexOf('/') + 1)
        } else {
            path = uri.path
        }
        return uri.host + path + fileName
    }

    companion object {
        private val TAG = MockInterceptor::class.java.simpleName
        private val FILE_EXTENSION = ".json"
    }
}
