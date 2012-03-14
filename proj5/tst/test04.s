	.global main
	.align 4
main:
!locals=2, max_args=0
	save %sp,-104,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (NAME wSZ)))]
	mov 4,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t1 assigned to reg %l1
	mov %o0,%l1
! [MOVE (MEM (TEMP 1)) (CONST 0)]
	mov 0,%l0
	st %l0,[%l1]
! [MOVE (VAR 1) (TEMP 1)]
	mov %l1,%l0
	st %l0,[%fp-4]
! [MOVE (VAR 2) (CONST 2)]
	mov 2,%l0
	st %l0,[%fp-8]
! [MOVE (FIELD (VAR 1) 0) (CONST 3)]
	mov 3,%l0
	ld [%fp-4],%l2
	st %l0,[%l2]
! [CALLST (NAME print) ( (BINOP + (VAR 2) (FIELD (VAR 1) 0)))]
	ld [%fp-8],%l0
	ld [%fp-4],%l3
	ld [%l3],%l4
	add %l0,%l4,%l0
	ld [%l0],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  5
!Total insts: 30
