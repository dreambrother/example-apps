-module(multiprocess_examples).
-export([ten_processes/0, receiver/0, interaction/0]).

% Process creation example
ten_processes() ->
        lists:foreach(fun(N) -> spawn(fun() -> io:format("~p~n", [N]) end) end, lists:seq(1, 10)).

% Simple interaction example
receiver() ->
        receive
                Msg -> io:format("Receive message: ~p~n", [Msg]),
                       receiver()
        end.

interaction() -> 
        Rcvr = spawn(fun() -> receiver() end),
        lists:foreach(fun(N) -> Rcvr ! N end, ["Foo", "Bar", "Hello, process neighbor!"]).
