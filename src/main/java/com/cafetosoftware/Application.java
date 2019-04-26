package com.cafetosoftware;

import io.javalin.Javalin;
import java.util.HashMap;
import java.util.Map;

public class Application
{
    static Map<String, String> reservations = new HashMap<String, String>() {{
        put("saturday", "No reservation");
        put("sunday", "No reservation");
    }};

    public static void main(String[] args)
    {
        Javalin app =
            Javalin.create()
            .enableStaticFiles("/public")
            .start(8080);

        app.post("/make-reservation", ctx -> {
            reservations.put( ctx.formParam("day"), ctx.formParam("time"));
            ctx.html("Your reseration has been saved");
        });

        app.get("/check-reservation", ctx -> {
            ctx.html(reservations.get(ctx.queryParam("day")));
        });
    }
}