	.global main
	.align 4
main:
!locals=1, max_args=0
	save %sp,-96,%sp
! [MOVE (VAR 1) (CONST 0)]
! [CJUMP <= (CONST 2) (CONST 1) (NAME L0)]
	nop
! [CJUMP <= (CONST 1) (CONST 0) (NAME L1)]
	nop
! [MOVE (VAR 1) (CONST 1)]
! [JUMP (NAME L2)]
	ba L2
	nop
! [LABEL L1]
! [MOVE (VAR 1) (CONST 2)]
! [LABEL L2]
! [LABEL L0]
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
!Total insts: 16
