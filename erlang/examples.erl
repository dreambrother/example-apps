-module(examples).
-export([iter/1]).

% Recursive iteration
iter([Elem]) -> io:format("Last elem: ~p~n", [Elem]);
iter([Head|Tail]) -> io:format("~p~n", [Head]), iter(Tail).
