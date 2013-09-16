-module(examples).
-export([iter/1, foo/1, bar/1, call_func/0, call_anonym_func/0, call_fun_with_exception/0]).

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

% High order function example
high_order_func(Func) -> Func({string, "Call from high order func"}).
call_func() -> high_order_func(fun bar/1).

% Anonymous function example
call_anonym_func() -> high_order_func(fun({X, Y}) -> io:format("~p~p~n", [X, Y]) end).

% Exception example
fun_with_try(Fun) -> 
    try Fun() of
        _ -> ok
    catch
        throw:Ex -> Ex
    end.
call_fun_with_exception() -> fun_with_try(fun() -> throw(some_error) end).

