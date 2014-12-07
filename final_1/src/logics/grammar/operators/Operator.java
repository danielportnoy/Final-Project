package logics.grammar.operators;

public abstract class Operator {
	
	public static enum BinOperatorEnum {
        or, 			// ||
        and, 			// &&
        binOr, 			// |
        binAnd, 		// &
        xor, 			// ^
        equals,			// ==
        notEquals, 		// !=
        less, 			// <
        greater, 		// >
        lessEquals, 	// <=
        greaterEquals, 	// >=
        lShift, 		// <<
        rSignedShift, 	// >>
        rUnsignedShift, // >>>
        plus, 			// +
        minus, 			// -
        times, 			// *
        divide, 		// /
        remainder, 		// %
    }	
}
