	.global main
	.align 4
main:
!locals=0, max_args=0
	save %sp,-96,%sp
! [CALLST (NAME print) ( (STRING "123"))]
	sethi %hi(L$2),%o0
	or %o0, %lo(L$2),%o0
	call printf
	nop
! [CALLST (NAME print) ( (CONST 123))]
! [CALLST (NAME print) ()]
! [CALLST (NAME print) ( (CONST 1))]

!Total regs:  1
!Total insts: 8
