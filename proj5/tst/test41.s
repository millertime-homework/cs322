	.global main
	.align 4
main:
!locals=1, max_args=0
	save %sp,-96,%sp
! [MOVE (VAR 1) (BINOP + (FLOAT 1.2) (BINOP * (FLOAT 2.5) (CONST 4)))]
! [CALLST (NAME print) ( (VAR 1))]
	sethi %hi(L$2),%o0
	or %o0, %lo(L$2),%o0
	call printf
	nop
	ret
	restore

L$1:	.asciz "%d\n"
L$2:	.asciz "\n"

!Total regs:  1
!Total insts: 12
