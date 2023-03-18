/**
 * 响应请求的返回结果类
 * code: 状态码，表示请求执行是否成功或者出错原因
 * msg: 返回结果的消息
 * data: 返回结果的数据
 */

package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private String code;
    private String msg;
    private Object data;

    public static Result success()
    {
        return new Result(ResultCode.CODE_200, "", null);
    }
    public static Result success(Object data)
    {
        return new Result(ResultCode.CODE_200, "", data);
    }
    public static Result success(String msg, Object data)
    {
        return new Result(ResultCode.CODE_200, msg, data);
    }
    public static Result error(String code, String msg)
    {
        return new Result(code, msg, null);
    }
    public static Result error(String code, String msg, Object data)
    {
        return new Result(code, msg, data);
    }
}
