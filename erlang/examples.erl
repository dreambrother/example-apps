-module(examples).
-export([iter/1, foo/1, bar/1]).

% Recursive iteration
iter([Elem]) -> io:format("Last elem: ~p~n", [Elem]);
iter([Head|Tail]) -> io:format("~p~n", [Head]), iter(Tail).

% Tuple pattern matching
foo(A = {B, C}) -> io:format("A:~p, B: ~p, C: ~p~n", [A, B, C]).

% Pattern matching in case statement
bar(Foo) ->
	case Foo of
		{number, N} when N < 0 -> io:format("Negative number: ~p~n", [N]);
		{number, N} when N >= 0 -> io:format("Positive number: ~p~n", [N]);
		{string, N} -> io:format("String: ~s~n", [N])
	end.
