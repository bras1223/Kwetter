package nl.luukhermans.service.interceptor;

import nl.luukhermans.domain.Message;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@TrendAnno
public class TrendInterceptor {


    @AroundInvoke
    public Object replaceMethod(InvocationContext context) throws Exception {
        Object[] objects = context.getParameters();
        Message message = (Message) objects[0];
        String messageString = message.getMessage();

        messageString = messageString.replaceAll("vet", "hard");
        messageString = messageString.replaceAll("cool", "dik");

        message.setMessage(messageString);

        objects[0] = message;

        context.setParameters(objects);

        return context.proceed();
    }

}

