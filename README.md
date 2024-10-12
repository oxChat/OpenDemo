# OpenDemo
 DEMO

DEMO地址：
https://github.com/oxChat/OpenDemo

68测试环境跳转授权

// clientId: 需要在68后台拿到的自己APP的标识，用于验证是否为可以被授权的合法APP

// redirectUri: 授权成功后返回的地址，如果是APP提供scheme，如果是浏览器提供url，用于校验, APP授权成功后会在回调上增加?code=xxx

// code_challenge: 用于pcke授权时自己服务端生成的参数

// code_challengeMethod: 用于pcke授权时使用的方法，通常为S256

// scope: 在注册应用时就确定的

// unique_id: 为了安全，服务端生成的，用于跟code_challenge匹配，反向找到codeVerifier的值，授权成功后调整时会带上这个参数

// customoctopusauth://page/auth?clientId=abcdef1234&code_challenge=xxxx&code_challengeMethod=xxx&scope=xxx&unique_id=xxx&redirectUri=xxx://xxx/xxx

h5的demo

// customoctopusauth://page/auth?clientId=abcdef1234&code_challenge=xxxx&code_challengeMethod=xxx&scope=xxx&unique_id=xxx&redirectUri=https://storage.googleapis.com/e68-package-dev/install/testAuthSuccess.html

// 授权成功后会回调：https://storage.googleapis.com/e68-package-dev/install/testAuthSuccess.html?code=xxx&unique_id=xxx

app的demo

// customoctopusauth://page/auth?clientId=abcdef1234&code_challenge=xxxx&code_challengeMethod=xxx&scope=xxx&unique_id=xxx&redirectUri=oauth://auth.example.com/success

// 授权成功后会回调：oauth://auth.example.com/success?code=xxx&unique_id=xxx
