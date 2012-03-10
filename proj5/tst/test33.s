	.global main
	.align 4
main:
!locals=3, max_args=0
	save %sp,-104,%sp
! [MOVE (VAR 1) (CONST 2)]
! [MOVE (VAR 2) (CONST 3)]
! [MOVE (VAR 3) (BINOP + (VAR 1) (VAR 2))]
! [CALLST (NAME print) ( (VAR 3))]
	sethi %hi(L$2),%o0
	or %o0, %lo(L$2),%o0
	call printf
	nop
! [CJUMP != (VAR 3) (CONST 5) (NAME L0)]
	nop
! [CALLST (NAME print) ( (STRING "OK"))]
	sethi %hi(L$3),%o0
	or %o0, %lo(L$3),%o0
	call printf
	nop
! [LABEL L0]
	ret
	restore

L$1:	.asciz "%d\n"
L$2:	.asciz "\n"
L$3:	.asciz "OK\n"

!Total regs:  1
!Total insts: 18
