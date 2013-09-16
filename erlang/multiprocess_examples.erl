-module(multiprocess_examples).
-export([ten_processes/0]).

% Process creation example
ten_processes() ->
        lists:foreach(fun(N) -> spawn(fun() -> io:format("~p~n", [N]) end) end, [1,2]).        
