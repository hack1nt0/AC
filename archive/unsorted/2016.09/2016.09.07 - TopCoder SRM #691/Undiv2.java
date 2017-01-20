package main;

public class Undiv2 {
    long[] BSUM = new long[]{1355758,
            1355777,1355764,1355778,1355769,1355767,1355769,1355781,1355773,1355773,1355771,1355772,1355770,1355772,1355769,1355777,1355774,1355772,1355774,1355775,1355763,1355774,1355774,1355772,1355775,1355776,1355772,1355765,1355774,1355767,1355776,1355772,1355776,1355773,1355769,1355774,1355770,1355768,1355777,1355768,1355776,1355770,1355781,1355770,1355771,1355775,1355768,1355769,1355775,1355778,1355773,1355765,1355778,1355770,1355766,1355774,1355780,1355771,1355777,1355771,1355772,1355769,1355771,1355771,1355776,1355777,1355775,1355777,1355764,1355770,1355770,1355771,1355776,1355779,1355769,1355770,1355772,1355771,1355767,1355778,1355769,1355774,1355776,1355769,1355774,1355775,1355768,1355775,1355770,1355776,1355773,1355780,1355765,1355768,1355773,1355769,1355777,1355773,1355776,1355773,1355768,
            1355772,1355772,1355768,1355774,1355774,1355779,1355770,1355772,1355769,1355772,1355767,1355776,1355775,1355780,1355768,1355778,1355765,1355773,1355769,1355770,1355775,1355774,1355773,1355774,1355767,1355774,1355770,1355773,1355774,1355778,1355775,1355765,1355784,1355766,1355773,1355769,1355776,1355772,1355776,1355773,1355769,1355770,1355770,1355770,1355778,1355771,1355776,1355776,1355767,1355770,1355775,1355765,1355774,1355779,1355770,1355776,1355768,1355772,1355767,1355777,1355766,1355776,1355776,1355771,1355774,1355769,1355768,1355774,1355778,1355772,1355774,1355777,1355772,1355775,1355771,1355768,1355772,1355772,1355775,1355779,1355770,1355770,1355772,1355769,1355769,1355774,1355780,1355769,1355769,1355779,1355763,1355775,1355773,1355769,1355774,1355774,1355775,1355766,1355779,1355771,
            1355766,1355777,1355772,1355773,1355777,1355769,1355771,1355773,1355768,1355773,1355778,1355771,1355774,1355773,1355769,1355772,1355778,1355765,1355777,1355776,1355771,1355774,1355772,1355769,1355766,1355783,1355765,1355782,1355776,1355766,1355772,1355774,1355766,1355774,1355775,1355773,1355771,1355773,1355768,1355773,1355772,1355770,1355770,1355783,1355766,1355780,1355766,1355773,1355769,1355771,1355774,1355773,1355779,1355770,1355771,1355773,1355769,1355770,1355779,1355770,1355776,1355774,1355771,1355770,1355775,1355764,1355776,1355776,1355775,1355767,1355783,1355765,1355769,1355773,1355775,1355766,1355780,1355775,1355766,1355775,1355770,1355771,1355776,1355768,1355779,1355775,1355766,1355776,1355768,1355776,1355766,1355780,1355767,1355776,1355780,1355764,1355770,1355779,1355762,1355776,
            1355776,1355774,1355775,1355773,1355768,1355777,1355767,1355770,1355776,1355774,1355770,1355773,1355777,1355761,1355775,1355771,1355773,1355780,1355771,1355770,1355771,1355772,1355766,1355777,1355774,1355772,1355777,1355769,1355774,1355768,1355777,1355768,1355769,1355781,1355772,1355775,1355773,1355766,1355780,1355769,1355769,1355777,1355776,1355771,1355771,1355772,1355774,1355767,1355779,1355770,1355771,1355780,1355763,1355779,1355768,1355766,1355774,1355778,1355771,1355777,1355769,1355770,1355771,1355773,1355765,1355782,1355777,1355771,1355775,1355775,1355762,1355775,1355774,1355770,1355774,1355781,1355764,1355773,1355771,1355767,1355779,1355766,1355779,1355771,1355777,1355766,1355773,1355772,1355771,1355777,1355775,1355768,1355779,1355768,1355766,1355779,1355769,1355772,1355778,1355774,
            1355779,1355767,1355775,1355766,1355770,1355778,1355769,1355781,1355769,1355769,1355771,1355775,1355769,1355772,1355774,1355776,1355769,1355777,1355766,1355775,1355771,1355771,1355771,1355778,1355769,1355776,1355772,1355764,1355772,1355777,1355764,1355782,1355778,1355769,1355772,1355771,1355768,1355771,1355778,1355772,1355775,1355777,1355763,1355773,1355778,1355761,1355780,1355774,1355769,1355778,1355767,1355773,1355775,1355769,1355774,1355772,1355780,1355771,1355770,1355769,1355772,1355774,1355772,1355771,1355779,1355768,1355774,1355774,1355771,1355770,1355773,1355769,1355776,1355772,1355778,1355772,1355766,1355771,1355775,1355772,1355776,1355776,1355768,1355778,1355764,1355777,1355768,1355776,1355772,1355776,1355771,1355768,1355774,1355775,1355761,1355782,1355771,1355773,1355773,1355768,
            1355773,1355774,1355764,1355778,1355772,1355774,1355775,1355768,1355777,1355767,1355781,1355762,1355775,1355778,1355773,1355774,1355772,1355764,1355775,1355773,1355774,1355774,1355776,1355774,1355769,1355768,1355771,1355775,1355774,1355773,1355776,1355769,1355772,1355772,1355772,1355771,1355771,1355782,1355767,1355777,1355764,1355776,1355771,1355768,1355775,1355772,1355776,1355774,1355766,1355778,1355767,1355768,1355775,1355772,1355775,1355778,1355767,1355773,1355767,1355775,1355770,1355780,1355777,1355768,1355780,1355768,1355769,1355775,1355769,1355777,1355776,1355770,1355771,1355771,1355770,1355769,1355778,1355770,1355775,1355773,1355771,1355771,1355773,1355771,1355770,1355773,1355773,1355775,1355779,1355761,1355776,1355774,1355762,1355775,1355778,1355774,1355774,1355766,1355775,1355770,
            1355769,1355779,1355771,1355776,1355773,1355776,1355771,1355768,1355773,1355777,1355766,1355783,1355770,1355774,1355765,1355775,1355765,1355774,1355779,1355774,1355764,1355785,1355767,1355770,1355770,1355776,1355769,1355777,1355770,1355773,1355775,1355770,1355769,1355772,1355771,1355774,1355782,1355767,1355771,1355770,1355774,1355767,1355779,1355768,1355778,1355776,1355763,1355774,1355779,1355763,1355777,1355775,1355775,1355772,1355774,1355766,1355769,1355776,1355769,1355775,1355778,1355766,1355776,1355771,1355765,1355775,1355768,1355778,1355774,1355777,1355769,1355775,1355773,1355765,1355771,1355777,1355772,1355778,1355777,1355770,1355769,1355772,1355769,1355776,1355774,1355775,1355772,1355770,1355766,1355774,1355774,1355771,1355777,1355778,1355773,1355764,1355779,1355766,1355768,1355778,
            1355771,1355774,1355775,1355769,1355771,1355771,1355773,1355769,1355777,1355774,1355770,1355774,1355767,1355772,1355778,1355763,1355781,1355777,1355767,1355773,1355774,1355769,1355768,1355776,1355769,1355777,1355779,1355766,1355771,1355774,1355764,1355776,1355774,1355773,1355773,1355779,1355764,1355771,1355775,1355770,1355770,1355777,1355771,1355778,1355767,1355776,1355765,1355773,1355771,1355777,1355774,1355771,1355772,1355774,1355767,1355772,1355775,1355771,1355774,1355775,1355770,1355772,1355774,1355767,1355771,1355774,1355779,1355771,1355776,1355763,1355776,1355767,1355772,1355773,1355781,1355770,1355771,1355773,1355772,1355772,1355776,1355768,1355774,1355776,1355768,1355774,1355770,1355768,1355774,1355775,1355768,1355783,1355774,1355769,1355767,1355780,1355761,1355780,1355776,1355771,
            1355774,1355773,1355772,1355772,1355771,1355768,1355775,1355777,1355768,1355778,1355768,1355768,1355773,1355770,1355773,1355779,1355773,1355770,1355769,1355775,1355766,1355780,1355771,1355773,1355780,1355767,1355771,1355774,1355771,1355768,1355772,1355777,1355773,1355773,1355775,1355765,1355773,1355773,1355770,1355776,1355778,1355769,1355773,1355768,1355773,1355765,1355779,1355772,1355781,1355771,1355768,1355776,1355770,1355764,1355776,1355777,1355768,1355777,1355773,1355766,1355771,1355779,1355762,1355779,1355777,1355776,1355771,1355770,1355766,1355780,1355774,1355771,1355773,1355782,1355762,1355777,1355769,1355766,1355778,1355770,1355772,1355777,1355774,1355769,1355773,1355769,1355768,1355773,1355781,1355767,1355781,1355767,1355769,1355776,1355767,1355770,1355779,1355772,1355773,1355769,
            1355778,1355764,1355771,1355774,1355774,1355781,1355775,1355767,1355773,1355776,1355768,1355775,1355774,1355771,1355773,1355779,1355766,1355770,1355771,1355777,1355764,1355780,1355773,1355774,1355770,1355770,1355767,1355777,1355768,1355780,1355773,1355770,1355773,1355770,1355768,1355777,1355773,1355774,1355771,1355778,1355767,1355769,1355779,1355762,1355774,1355778,1355774,1355775,1355766,1355774,1355773,1355768,1355776,1355775,1355776,1355771,1355770,1355773,1355771,1355771,1355773,1355775,1355775,1355772,1355776,1355769,1355773,1355768,1355774,1355770,1355774,1355776,1355773,1355770,1355769,1355766,1355778,1355768,1355779,1355774,1355769,1355777,1355765,1355773,1355769,1355776,1355774,1355779,1355770,1355769,1355769,1355777,1355764,1355778,1355779,1355774,1355775,1355766,1355771,1355778,
            1355762,1355777,1355774,1355778,1355771,1355773,1355767,1355772,1355775,1355768,1355776,1355774,1355771,1355774,1355775,1355764,1355771,1355774,1355776,1355775,1355776,1355771,1355771,1355766,1355770,1355775,1355775,1355776,1355775,1355766,1355777,1355768,1355776,1355767,1355773,1355777,1355769,1355778,1355770,1355772,1355773,1355770,1355771,1355773,1355773,1355780,1355761,1355777,1355772,1355768,1355774,1355772,1355774,1355777,1355766,1355776,1355769,1355771,1355769,1355778,1355774,1355772,1355777,1355769,1355767,1355770,1355776,1355772,1355780,1355770,1355772,1355776,1355765,1355772,1355782,1355766,1355778,1355774,1355767,1355774,1355776,1355769,1355770,1355777,1355774,1355770,1355780,1355765,1355769,1355778,1355765,1355774,1355776,1355769,1355781,1355765,1355773,1355772,1355769,1355771,
            1355773,1355778,1355773,1355775,1355774,1355767,1355770,1355775,1355770,1355781,1355770,1355773,1355770,1355773,1355765,1355775,1355775,1355775,1355767,1355778,1355766,1355773,1355772,1355773,1355771,1355782,1355770,1355770,1355774,1355771,1355769,1355779,1355765,1355779,1355776,1355772,1355773,1355771,1355771,1355764,1355784,1355767,1355778,1355778,1355765,1355773,1355772,1355764,1355778,1355774,1355773,1355773,1355773,1355768,1355771,1355774,1355771,1355772,1355783,1355763,1355775,1355774,1355766,1355771,1355772,1355775,1355772,1355775,1355774,1355773,1355768,1355773,1355771,1355771,1355774,1355780,1355772,1355768,1355773,1355773,1355766,1355774,1355780,1355771,1355772,1355772,1355764,1355778,1355773,1355776,1355769,1355776,1355775,1355765,1355780,1355764,1355769,1355779,1355770,1355776,
            1355775,1355767,1355771,1355776,1355768,1355773,1355777,1355774,1355767,1355774,1355773,1355770,1355778,1355766,1355775,1355778,1355770,1355775,1355773,1355768,1355774,1355772,1355767,1355779,1355779,1355766,1355775,1355765,1355773,1355773,1355774,1355772,1355774,1355774,1355763,1355777,1355772,1355767,1355775,1355779,1355767,1355779,1355766,1355774,1355773,1355771,1355771,1355778,1355771,1355774,1355768,1355779,1355764,1355776,1355774,1355769,1355781,1355771,1355768,1355773,1355775,1355768,1355770,1355778,1355775,1355767,1355781,1355765,1355771,1355769,1355780,1355769,1355779,1355770,1355775,1355768,1355769,1355772,1355775,1355768,1355778,1355776,1355767,1355772,1355773,1355768,1355771,1355775,1355772,1355775,1355777,1355765,1355771,1355777,1355760,1355782,1355771,1355777,1355776,1355774,
            1355766,1355781,1355773,1355767,1355775,1355778,1355767,1355775,1355773,1355765,1355774,1355770,1355772,1355782,1355769,1355773,1355768,1355773,1355766,1355778,1355773,1355770,1355778,1355771,1355770,1355771,1355775,1355767,1355771,1355777,1355771,1355775,1355772,1355769,1355771,1355774,1355771,1355778,1355776,1355771,1355768,1355776,1355774,1355768,1355780,1355767,1355779,1355771,1355767,1355776,1355769,1355771,1355771,1355773,1355774,1355776,1355773,1355763,1355778,1355774,1355764,1355780,1355779,1355766,1355774,1355775,1355762,1355776,1355773,1355772,1355774,1355777,1355769,1355774,1355773,1355765,1355774,1355775,1355772,1355776,1355771,1355773,1355767,1355776,1355768,1355776,1355777,1355772,1355776,1355766,1355773,1355769,1355771,1355774,1355774,1355773,1355776,1355770,1355772,1355767,
            1355771,1355773,1355774,1355772,1355781,1355767,1355772,1355772,1355775,1355766,1355777,1355777,1355767,1355778,1355773,1355772,1355769,1355772,1355773,1355777,1355773,1355770,1355771,1355771,1355765,1355780,1355766,1355780,1355778,1355773,1355769,1355773,1355767,1355771,1355775,1355774,1355774,1355776,1355765,1355772,1355774,1355766,1355773,1355778,1355771,1355776,1355771,1355771,1355768,1355769,1355776,1355775,1355778,1355770,1355772,1355767,1355772,1355774,1355772,1355774,1355774,1355771,1355776,1355767,1355773,1355770,1355770,1355773,1355775,1355776,1355774,1355769,1355773,1355764,1355777,1355769,1355781,1355771,1355771,1355773,1355767,1355774,1355771,1355773,1355772,1355778,1355772,1355766,1355774,1355774,1355766,1355778,1355773,1355776,1355771,1355767,1355773,1355775,1355763,1355781,
            1355772,1355774,1355768,1355779,1355766,1355770,1355776,1355767,1355773,1355778,1355770,1355776,1355770,1355771,1355771,1355773,1355773,1355774,1355776,1355770,1355774,1355765,1355771,1355775,1355776,1355770,1355777,1355777,1355768,1355771,1355776,1355767,1355773,1355779,1355775,1355771,1355771,1355766,1355779,1355769,1355772,1355779,1355770,1355773,1355764,1355782,1355765,1355768,1355774,1355775,1355773,1355777,1355767,1355773,1355773,1355769,1355769,1355784,1355770,1355774,1355778,1355767,1355766,1355775,1355770,1355773,1355778,1355771,1355774,1355771,1355768,1355768,1355779,1355768,1355777,1355779,1355763,1355778,1355771,1355766,1355774,1355773,1355771,1355773,1355786,1355762,1355771,1355779,1355764,1355770,1355778,1355774,1355773,1355768,1355774,1355773,1355768,1355769,1355779,1355773,
            1355772,1355775,1355777,1355763,1355774,1355775,1355773,1355778,1355775,1355773,1355764,1355774,1355766,1355775,1355777,1355775,1355769,1355777,1355765,1355773,1355770,1355775,1355769,1355777,1355771,1355772,1355775,1355771,1355764,1355781,1355764,1355779,1355773,1355776,1355765,1355772,1355771,1355768,1355779,1355769,1355778,1355780,1355764,1355774,1355779,1355763,1355775,1355777,1355775,1355773,1355775,1355763,1355773,1355771,1355772,1355772,1355783,1355764,1355778,1355771,1355767,1355771,1355773,1355772,1355772,1355778,1355769,1355772,1355773,1355766,1355773,1355773,1355777,1355776,1355768,1355777,1355768,1355774,1355765,1355779,1355774,1355771,1355777,1355770,1355767,1355774,1355772,1355775,1355774,1355777,1355773,1355770,1355771,1355771,1355767,1355776,1355773,1355779,1355777,1355762,
            1355776,1355771,1355769,1355776,1355773,1355772,1355772,1355777,1355762,1355774,1355775,1355766,1355776,1355776,1355771,1355773,1355775,1355767,1355770,1355774,1355766,1355782,1355778,1355765,1355779,1355770,1355763,1355773,1355777,1355773,1355776,1355772,1355773,1355768,1355776,1355767,1355773,1355776,1355768,1355787,1355763,1355773,1355772,1355771,1355768,1355778,1355774,1355772,1355770,1355774,1355766,1355775,1355772,1355772,1355779,1355775,1355767,1355776,1355767,1355772,1355769,1355780,1355770,1355776,1355776,1355765,1355775,1355767,1355774,1355769,1355785,1355768,1355771,1355773,1355772,1355773,1355773,1355769,1355777,1355773,1355767,1355775,1355774,1355766,1355776,1355772,1355775,1355771,1355781,1355761,1355775,1355774,1355762,1355781,1355771,1355773,1355774,1355773,1355769,1355772,
            1355772,1355769,1355774,1355778,1355768,1355781,1355770,1355769,1355770,1355774,1355770,1355785,1355769,1355774,1355770,1355770,1355767,1355784,1355767,1355774,1355779,1355768,1355769,1355775,1355772,1355766,1355771,1355783,1355767,1355774,1355775,1355766,1355774,1355769,1355775,1355772,1355775,1355776,1355766,1355777,1355775,1355765,1355777,1355772,1355776,1355774,1355769,1355773,1355775,1355764,1355775,1355774,1355772,1355772,1355774,1355773,1355766,1355772,1355771,1355774,1355779,1355773,1355772,1355771,1355769,1355773,1355772,1355776,1355774,1355780,1355764,1355777,1355772,1355774,1355768,1355770,1355775,1355776,1355774,1355773,1355766,1355776,1355767,1355774,1355775,1355774,1355775,1355769,1355766,1355777,1355767,1355776,1355774,1355770,1355778,1355765,1355780,1355764,1355769,1355773,
            1355776,1355776,1355774,1355766,1355776,1355771,1355771,1355768,1355781,1355775,1355768,1355777,1355771,1355766,1355774,1355773,1355771,1355775,1355778,1355771,1355768,1355769,1355769,1355780,1355768,1355782,1355770,1355775,1355768,1355773,1355770,1355770,1355777,1355772,1355772,1355778,1355766,1355772,1355773,1355769,1355773,1355777,1355771,1355779,1355766,1355773,1355770,1355772,1355774,1355773,1355780,1355767,1355771,1355775,1355769,1355776,1355769,1355774,1355775,1355770,1355776,1355769,1355773,1355769,1355773,1355771,1355776,1355774,1355773,1355770,1355766,1355772,1355775,1355769,1355780,1355771,1355774,1355770,1355769,1355770,1355772,1355777,1355777,1355774,1355773,1355766,1355774,1355773,1355763,1355782,1355776,1355774,1355777,1355763,1355776,1355776,1355762,1355778,1355773,1355777,
            1355770,1355775,1355764,1355777,1355773,1355768,1355774,1355783,1355764,1355777,1355772,1355767,1355770,1355777,1355765,1355777,1355776,1355773,1355770,1355768,1355769,1355773,1355777,1355770,1355777,1355771,1355773,1355769,1355776,1355764,1355776,1355776,1355771,1355774,1355777,1355768,1355775,1355771,1355776,1355774,1355776,1355772,1355764,1355778,1355769,1355769,1355777,1355768,1355774,1355779,1355764,1355774,1355770,1355777,1355762,1355779,1355774,1355774,1355777,1355767,1355766,1355774,1355769,1355776,1355778,1355768,1355775,1355772,1355764,1355772,1355778,1355771,1355773,1355783,1355765,1355773,1355775,1355769,1355772,1355777,1355776,1355771,1355778,1355762,1355773,1355776,1355763,1355778,1355773,1355772,1355774,1355770,1355774,1355768,1355777,1355769,1355772,1355778,1355774,1355770,
            1355775,1355764,1355776,1355770,1355773,1355777,1355773,1355774,1355769,1355768,1355772,1355770,1355780,1355772,1355770,1355777,1355765,1355774,1355770,1355774,1355774,1355782,1355768,1355771,1355774,1355770,1355769,1355775,1355768,1355776,1355776,1355775,1355761,1355780,1355770,1355769,1355771,1355774,1355776,1355776,1355765,1355778,1355771,1355764,1355780,1355772,1355772,1355775,1355775,1355766,1355775,1355774,1355771,1355773,1355782,1355765,1355778,1355767,1355769,1355770,1355775,1355773,1355778,1355768,1355777,1355773,1355771,1355765,1355778,1355772,1355770,1355779,1355770,1355769,1355773,1355773,1355764,1355776,1355775,1355776,1355771,1355772,1355766,1355777,1355769,1355772,1355774,1355778,1355772,1355771,1355774,1355766,1355768,1355778,1355770,1355780,1355775,1355766,1355770,1355771,
            1355770,1355774,1355776,1355774,1355768,1355780,1355763,1355775,1355774,1355764,1355779,1355774,1355770,1355775,1355774,1355767,1355769,1355773,1355771,1355777,1355778,1355767,1355774,1355771,1355766,1355776,1355777,1355767,1355782,1355772,1355764,1355770,1355777,1355767,1355776,1355774,1355772,1355778,1355765,1355773,1355770,1355770,1355772,1355778,1355773,1355769,1355773,1355776,1355762,1355779,1355774,1355772,1355775,1355773,1355767,1355774,1355773,1355770,1355765,1355784,1355771,1355774,1355772,1355770,1355775,1355771,1355770,1355774,1355779,1355772,1355774,1355770,1355769,1355768,1355782,1355766,1355778,1355776,1355769,1355771,1355770,1355769,1355774,1355772,1355771,1355776,1355779,1355763,1355778,1355769,1355767,1355774,1355778,1355771,1355775,1355775,1355769,1355773,1355769,1355771,
            1355773,1355776,1355771,1355773,1355772,1355772,1355769,1355771,1355772,1355782,1355772,1355773,1355767,1355775,1355765,1355776,1355773,1355776,1355773,1355770,1355775,1355771,1355771,1355774,1355770,1355774,1355774,1355771,1355773,1355767,1355772,1355774,1355771,1355776,1355774,1355774,1355767,1355774,1355775,1355766,1355777,1355777,1355772,1355776,1355773,1355770,1355771,1355769,1355770,1355776,1355775,1355775,1355774,1355765,1355773,1355771,1355768,1355775,1355782,1355767,1355775,1355766,1355773,1355769,1355775,1355769,1355775,1355780,1355764,1355776,1355771,1355769,1355770,1355775,1355772,1355776,1355772,1355778,1355765,1355774,1355774,1355776,1355773,1355776,1355771,1355773,1355766,1355778,1355763,1355777,1355774,1355777,1355769,1355771,1355776,1355767,1355768,1355775,1355775,1355770,
            1355778,1355769,1355769,1355770,1355777,1355767,1355776,1355774,1355777,1355771,1355768,1355771,1355774,1355770,1355774,1355775,1355778,1355766,1355778,1355766,1355767,1355779,1355767,1355779,1355776,1355774,1355769,1355774,1355767,1355771,1355772,1355779,1355771,1355777,1355769,1355772,1355776,1355764,1355774,1355782,1355766,1355777,1355771,1355771,1355767,1355772,1355769,1355777,1355778,1355771,1355769,1355771,1355771,1355771,1355776,1355770,1355773,1355777,1355772,1355772,1355771,1355769,1355774,1355769,1355775,1355779,1355773,1355770,1355770,1355771,1355773,1355769,1355783,1355769,1355770,1355780,1355766,1355771,1355776,1355768,1355777,1355773,1355774,1355767,1355773,1355773,1355765,1355775,1355774,1355775,1355782,1355761,1355776,1355775,1355760,1355779,1355776,1355771,1355773,1355779,
            1355764,1355773,1355774,1355770,1355770,1355780,1355769,1355777,1355770,1355769,1355772,1355778,1355766,1355779,1355775,1355769,1355770,1355773,1355762,1355779,1355772,1355774,1355776,1355771,1355773,1355765,1355778,1355763,1355777,1355777,1355774,1355775,1355768,1355765,1355777,1355768,1355775,1355774,1355777,1355771,1355766,1355777,1355775,1355765,1355780,1355768,1355776,1355780,1355768,1355772,1355771,1355771,1355768,1355784,1355770,1355772,1355777,1355771,1355764,1355775,1355770,1355773,1355778,1355771,1355773,1355774,1355764,1355770,1355777,1355769,1355777,1355778,1355762,1355780,1355769,1355777,1355767,1355777,1355773,1355770,1355783,1355760,1355773,1355779,1355764,1355775,1355774,1355773,1355775,1355766,1355772,1355777,1355767,1355770,1355776,1355774,1355773,1355773,1355777,1355765,
            1355773,1355770,1355774,1355785,1355772,1355772,1355774,1355766,1355766,1355781,1355775,1355772,1355774,1355770,1355772,1355770,1355773,1355772,1355770,1355780,1355769,1355774,1355773,1355770,1355769,1355775,1355768,1355780,1355771,1355775,1355767,1355772,1355771,1355772,1355773,1355770,1355773,1355783,1355759,1355779,1355775,1355763,1355775,1355775,1355772,1355778,1355772,1355769,1355771,1355771,1355769,1355774,1355783,1355764,1355780,1355769,1355767,1355772,1355774,1355770,1355783,1355771,1355770,1355771,1355774,1355766,1355777,1355771,1355776,1355774,1355771,1355769,1355775,1355772,1355768,1355779,1355774,1355770,1355775,1355772,1355765,1355776,1355765,1355782,1355771,1355779,1355772,1355770,1355775,1355765,1355770,1355780,1355768,1355779,1355775,1355764,1355773,1355772,1355769,1355776,
            1355773,1355777,1355767,1355777,1355768,1355771,1355772,1355766,1355779,1355776,1355767,1355778,1355772,1355764,1355774,1355774,1355764,1355784,1355779,1355766,1355772,1355775,1355767,1355769,1355776,1355774,1355775,1355778,1355768,1355771,1355777,1355762,1355783,1355769,1355770,1355781,1355766,1355773,1355771,1355768,1355773,1355775,1355782,1355767,1355771,1355773,1355769,1355773,1355772,1355775,1355774,1355772,1355767,1355777,1355767,1355775,1355765,1355778,1355773,1355775,1355774,1355769,1355769,1355774,1355770,1355772,1355776,1355777,1355767,1355777,1355767,1355776,1355773,1355775,1355776,1355774,1355772,1355765,1355778,1355768,1355770,1355774,1355777,1355772,1355776,1355764,1355776,1355769,1355769,1355774,1355777,1355769,1355776,1355770,1355774,1355768,1355775,1355769,1355769,1355782,
            1355768,1355774,1355770,1355771,1355770,1355770,1355776,1355777,1355768,1355784,1355765,1355771,1355767,1355784,1355770,1355776,1355776,1355772,1355766,1355773,1355772,1355770,1355771,1355779,1355772,1355770,1355771,1355771,1355770,1355771,1355779,1355773,1355774,1355774,1355767,1355773,1355773,1355766,1355779,1355769,1355777,1355773,1355771,1355771,1355772,1355771,1355770,1355775,1355776,1355770,1355774,1355771,1355767,1355775,1355768,1355777,1355780,1355770,1355778,1355766,1355771,1355768,1355779,1355769,1355775,1355775,1355769,1355773,1355770,1355773,1355769,1355771,1355774,1355776,1355772,1355770,1355774,1355774,1355767,1355776,1355774,1355775,1355771,1355771,1355767,1355780,1355765,1355782,1355769,1355779,1355771,1355770,1355774,1355767,1355773,1355771,1355771,1355779,1355776,1355768,
            1355771,1355775,1355770,1355766,1355785,1355771,1355769,1355775,1355771,1355767,1355775,1355770,1355772,1355776,1355773,1355770,1355777,1355768,1355765,1355780,1355769,1355774,1355779,1355767,1355773,1355772,1355771,1355772,1355772,1355773,1355773,1355780,1355762,1355774,1355772,1355767,1355774,1355778,1355769,1355777,1355774,1355767,1355769,1355776,1355769,1355776,1355776,1355771,1355768,1355774,1355768,1355776,1355769,1355773,1355777,1355771,1355773,1355770,1355775,1355765,1355772,1355779,1355770,1355779,1355773,1355767,1355768,1355770,1355775,1355770,1355781,1355772,1355771,1355773,1355769,1355768,1355774,1355771,1355776,1355776,1355772,1355768,1355774,1355769,1355769,1355777,1355778,1355771,1355780,1355765,1355774,1355769,1355765,1355777,1355776,1355775,1355770,1355776,1355763,1355775,
            1355779,1355769,1355774,1355781,1355764,1355778,1355770,1355770,1355769,1355775,1355771,1355778,1355777,1355770,1355767,1355777,1355760,1355780,1355770,1355777,1355771,1355775,1355769,1355768,1355778,1355766,1355775,1355774,1355774,1355772,1355775,1355768,1355775,1355767,1355775,1355776,1355777,1355767,1355769,1355775,1355772,1355764,1355784,1355768,1355774,1355776,1355771,1355769,1355773,1355773,1355767,1355776,1355775,1355775,1355773,1355766,1355777,1355769,1355769,1355778,1355773,1355772,1355772,1355776,1355761,1355774,1355778,1355767,1355776,1355778,1355767,1355774,1355772,1355771,1355772,1355774,1355773,1355783,1355771,1355767,1355770,1355778,1355763,1355777,1355780,1355765,1355777,1355768,1355774,1355773,1355767,1355770,1355776,1355776,1355770,1355774,1355772,1355767,1355773,1355771,
            1355774,1355775,1355775,1355772,1355770,1355768,1355769,1355777,1355773,1355771,1355778,1355773,1355765,1355776,1355771,1355771,1355779,1355775,1355775,1355770,1355777,1355765,1355770,1355776,1355768,1355780,1355772,1355774,1355766,1355776,1355770,1355770,1355771,1355775,1355774,1355776,1355761,1355781,1355772,1355762,1355775,1355779,1355767,1355778,1355773,1355771,1355767,1355778,1355764,1355776,1355781,1355768,1355779,1355765,1355768,1355770,1355779,1355769,1355780,1355770,1355775,481741};

    public long getsum(int n) {
        int MAXN = (int)1e9;
        int BSIZE = (int)Math.sqrt(MAXN);
        int K = 10;
        BSIZE *= K;
        int BNUM = (MAXN + BSIZE - 1) / BSIZE;
        int[] BSNUM = new int[BNUM];

/*        long[] BSUM = new long[BNUM];
        for (int i = 1; i <= MAXN; ++i) {
            long si = s(i);
            BSUM[i / BSIZE] += si;
            ++BSNUM[i / BSIZE];
        }
        for (int i = 0; i < BSUM.length; ++i) {
            System.out.print(BSUM[i]);
            if (i < BSUM.length - 1) System.out.print(",");
            if (i % 100 == 0) {
                System.out.println();
            }
        }
        System.out.println();

        for (int i = 0; i < BSUM.length; ++i)
            System.out.println(BSNUM[i]);*/


           long res = 0;
        for (int i = 0; i < BSUM.length; ++i) {
            int rb = (i + 1) * BSIZE - 1;
            if (rb <= n) {
                res += BSUM[i];
                continue;
            }
            for (int j = i * BSIZE; j <= n; ++j) {
                long sj = s(j);
                res += sj;
            }
            break;
        }

        return res;
    }

    private long s(int i) {
        if (i == 0) return 0;
        long si = 0;
        for (int j = 2; ;++j) {
            try {
                if (i % j == 0) continue;
            } catch (Exception e) {
                System.out.println(i + " % " + j);
                e.printStackTrace();
            }
            if (si == 1) {
                si = j;
                break;
            }
            ++si;
        }
        return si;
    }
}