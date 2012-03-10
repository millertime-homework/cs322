	.align 4
A_go:
!locals=1, max_args=2
	save %sp,-96,%sp
! [MOVE (VAR 1) (CONST 0)]
! [CJUMP <= (PARAM 1) (CONST 0) (NAME L0)]
	nop
! [CALLST (NAME print) ( (PARAM 1))]
	sethi %hi(L$2),%o0
	or %o0, %lo(L$2),%o0
	call printf
	nop
! [MOVE (TEMP 2) (CALL (NAME A_back) ( (PARAM 0) (BINOP - (PARAM 1) (CONST 1))))]
	mov 