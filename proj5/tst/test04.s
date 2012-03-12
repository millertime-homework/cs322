	.global main
	.align 4
main:
!locals=2, max_args=0
	save %sp,-104,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (NAME wSZ)))]
	mov 4,%o0
	call malloc
	nop
!>> Temp t1 assigned to reg %l1
	mov %i0,%l1
! [MOVE (MEM (TEMP 1)) (CONST 0)]
	st %l0,[%l1]
! [MOVE (VAR 1) (TEMP 1)]
	st %l0,[%fp-4]
! [MOVE (VAR 2) (CONST 2)]
	st %l0,[%fp-8]
! [MOVE (FIELD (VAR 1) 0) (CONST 3)]
	ld [%fp-4],%l2
	st %l0,[%l2]
! [CALLST (NAME print) ( (BINOP + (VAR 2) (FIELD (VAR 1) 0)))]
	sethi %hi(L$2),%o0
	or %o0, %lo(L$2),%o0
	call printf
	nop
	ret
	restore

L$1:	.asciz "%d\n"
L$2:	.asciz "\n"

!Total regs:  3
!Total insts: 21
