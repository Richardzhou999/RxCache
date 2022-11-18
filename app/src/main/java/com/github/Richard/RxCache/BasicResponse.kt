package com.github.Richard.RxCache

/**
 * @Author Charlie
 * @Date 2022/8/11 15:57
 */
class BasicResponse<T> {

     val code = 0
     val msg: String? = null
     val data: T? = null
     val datas: T? = null
     val result: T? = null
     val success: Boolean
          get() = code == SUCCESS_CODE

     override fun toString(): String {
          return "BaseResponse{" +
                  "code=" + code +
                  ", msg='" + msg + '\'' +
                  ", result=" + result +
                  ", data=" + data +
                  ", datas=" + datas +
                  '}'
     }

     companion object {
          private const val SUCCESS_CODE = 200
     }

}